package com.nani.gamesForKids.Helper;


import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class MultiTouchCatcher {

    public static boolean caughtARect(MotionEvent motionEvent, Rect rect) {

        for (int i = 0; i < motionEvent.getPointerCount(); i++) {

            if (rect.contains((int) motionEvent.getX(i), (int) motionEvent.getY(i))) {

                return true;
            }
        }

        return rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public static boolean caughtARectF(MotionEvent motionEvent, RectF rectF) {

        for (int i = 0; i < motionEvent.getPointerCount(); i++) {

            if (rectF.contains(motionEvent.getX(i), motionEvent.getY(i))) {

                return true;
            }
        }

        return rectF.contains(motionEvent.getX(), motionEvent.getY());
    }
}
