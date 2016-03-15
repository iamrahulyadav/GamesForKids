package com.nani.dots;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BoardView.BoardViewSizeChangedListener, View.OnTouchListener {

    @Bind(R.id.boardView) BoardView boardView;
    Random random;
    int minDotRadius, maxDotRadius;
    int[] dotColors;
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.mDecorView = getWindow().getDecorView();

        this.random = new Random();
        setupColors();
    }

    private void setupColors() {
        this.dotColors = getResources().getIntArray(R.array.rainbow);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    private void createADot() {
        this.boardView.setDot(getNewDot());
        this.boardView.invalidate();
    }

    private void setupRadius() {
        this.minDotRadius = this.boardView.getWidth()/8;
        this.maxDotRadius = this.boardView.getWidth()/4;
    }

    private Dot getNewDot() {
        Dot dot = new Dot();

        newRadiusForDot(dot);
        newCenterXForDot(dot, dot.getRadius());
        newCenterYForDot(dot, dot.getRadius());
        newColorForDot(dot);

        return dot;
    }

    private void newCenterXForDot(Dot dot, int radius) {
        dot.setCenterX(this.random.nextInt(this.boardView.getWidth() - radius * 2) + radius);

        if (this.boardView.getDot() != null && Math.abs(dot.getCenterX() - this.boardView.getDot().getCenterX()) <= this.maxDotRadius) {
            newCenterXForDot(dot, radius);
        }
    }

    private void newCenterYForDot(Dot dot, int radius) {
        dot.setCenterY(this.random.nextInt(this.boardView.getHeight() - radius * 2) + radius);

        if (this.boardView.getDot() != null && Math.abs(dot.getCenterY() - this.boardView.getDot().getCenterY()) <= this.maxDotRadius) {
            newCenterYForDot(dot, radius);
        }
    }

    private void newRadiusForDot(Dot dot) {
        dot.setRadius(random.nextInt(maxDotRadius - minDotRadius) + minDotRadius);
    }

    private void newColorForDot(Dot dot) {
        dot.setColor(dotColors[random.nextInt(dotColors.length)]);
    }

    @Override
    public void onSizeChanged() {
        setupRadius();
        createADot();

        this.boardView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            tryToCatch(event);
        }

        return true;
    }

    private void tryToCatch(MotionEvent motionEvent) {

        if (caughtADot(motionEvent)) {
            createADot();
        }
    }

    private boolean caughtADot(MotionEvent motionEvent) {
        Dot dot = this.boardView.getDot();
        Rect circleRect = new Rect(dot.getCenterX() - dot.getRadius(),
                dot.getCenterY() - dot.getRadius(),
                dot.getCenterX() + dot.getRadius(),
                dot.getCenterY() + dot.getRadius());

        return circleRect.contains((int)motionEvent.getX(), (int)motionEvent.getY());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
