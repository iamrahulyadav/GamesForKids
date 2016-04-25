package com.nani.gamesForKids.Games.Face;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.nani.gamesForKids.Helper.MultiTouchCatcher;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public boolean onTouch(View v, MotionEvent motionEvent) {
        Iterator iterator = this.face.getFacePartListHashMap().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry rects = (Map.Entry) iterator.next();

            for (Rect rect: (List<Rect>) rects.getValue()) {

                if (MultiTouchCatcher.caughtARect(motionEvent, rect)) {
                    this.onFacePartClickListener.clickedOn(rects.getKey().toString());

                    return true;
                }
            }
        }

        return true;
    }

    public interface OnFacePartClickListener {
        void clickedOn(String facePartName);
    }
}
