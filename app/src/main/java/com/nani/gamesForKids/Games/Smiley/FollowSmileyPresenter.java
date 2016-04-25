package com.nani.gamesForKids.Games.Smiley;

import android.content.Context;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import com.nani.gamesForKids.Core.MVPView;
import com.nani.gamesForKids.Core.Presenter;
import com.nani.gamesForKids.Helper.MultiTouchCatcher;
import com.nani.gamesForKids.R;
import com.nani.gamesForKids.Games.Smiley.Helpers.Smiley;

import java.util.Random;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class FollowSmileyPresenter implements Presenter, View.OnTouchListener {

    private Random random;
    private int minSmileyRadius, maxSmileyRadius;
    private int[] smileyColors, backgroundColors;
    private int viewWidth, viewHeight;
    private Smiley oldSmiley;

    private Vibrator vibrator;
    private long vibrationTime = 200;

    private FollowSmileyView view;

    @Override
    public void attachView(Object view) {
        this.view = (FollowSmileyView) view;
    }

    @Override
    public void start() {
        this.random = new Random();
        setupColors();
        setupVibrator();
    }

    @Override
    public void stop() {

    }

    public void setSizeOfView(int viewWidth, int viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;

        setupRadius();
        createASmiley();
    }

    private void setupColors() {
        this.smileyColors = this.view.getContext().getResources().getIntArray(R.array.smileyColors);
        this.backgroundColors = this.view.getContext().getResources().getIntArray(R.array.smileyBackgroundColors);
    }

    private void setupVibrator() {
        this.vibrator = (Vibrator) this.view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void setupRadius() {
        this.minSmileyRadius = this.viewWidth / 8;
        this.maxSmileyRadius = this.viewWidth / 4;
    }

    private void createASmiley() {
        Smiley smiley = getNewSmiley();

        this.view.setSmileyForView(smiley);
        this.oldSmiley = smiley;
    }

    private Smiley getNewSmiley() {
        Smiley smiley = new Smiley();

        newRadiusForSmiley(smiley);
        newCenterXForSmiley(smiley, smiley.getRadius());
        newCenterYForSmiley(smiley, smiley.getRadius());
        newColorForSmiley(smiley);

        return smiley;
    }

    private void newCenterXForSmiley(Smiley smiley, float radius) {
        smiley.setCenterX(this.random.nextInt(this.viewWidth - (int) radius * 2) + radius);

        if (this.oldSmiley != null && Math.abs(smiley.getCenterX() - this.oldSmiley.getCenterX()) <= this.maxSmileyRadius) {
            newCenterXForSmiley(smiley, radius);
        }
    }

    private void newCenterYForSmiley(Smiley smiley, float radius) {
        smiley.setCenterY(this.random.nextInt(this.viewHeight - (int) radius * 2) + radius);

        if (this.oldSmiley != null && Math.abs(smiley.getCenterY() - this.oldSmiley.getCenterY()) <= this.maxSmileyRadius) {
            newCenterYForSmiley(smiley, radius);
        }
    }

    private void newRadiusForSmiley(Smiley smiley) {
        smiley.setRadius(random.nextInt(maxSmileyRadius - minSmileyRadius) + minSmileyRadius);
    }

    private void newColorForSmiley(Smiley smiley) {
        int arrayIndex = random.nextInt(this.smileyColors.length);
        smiley.setColor(this.smileyColors[arrayIndex]);

        this.view.setBackgroundColor(this.backgroundColors[arrayIndex]);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {
            tryToCatch(event);
        }

        return true;
    }

    private void tryToCatch(MotionEvent motionEvent) {

        if (caughtASmiley(motionEvent)) {
            this.vibrator.vibrate(this.vibrationTime);
            createASmiley();
        }
    }

    private boolean caughtASmiley(MotionEvent motionEvent) {
        return MultiTouchCatcher.caughtARectF(motionEvent, this.oldSmiley.getRectF());
    }

    public interface FollowSmileyView extends MVPView {

        void setSmileyForView(Smiley smiley);

        void setBackgroundColor(int color);
    }
}
