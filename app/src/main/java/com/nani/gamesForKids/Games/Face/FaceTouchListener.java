package com.nani.gamesForKids.Games.Face;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class FaceTouchListener implements View.OnTouchListener {

    private OnFacePartClickListener onFacePartClickListener;
    private Face face;

    public FaceTouchListener(Context context, Face face) {
        this.onFacePartClickListener = (OnFacePartClickListener) context;
        this.face = face;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //tryToCatch()
        return false;
    }

    public interface OnFacePartClickListener {
        void clickedOn(FacePart facePart);
    }
}
