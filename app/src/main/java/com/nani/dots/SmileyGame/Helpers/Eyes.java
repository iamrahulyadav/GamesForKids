package com.nani.dots.SmileyGame.Helpers;

import android.graphics.RectF;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class Eyes {
    RectF smileyRect;
    Eye leftEye;
    Eye rightEye;

    public Eyes(Smiley smiley) {
        this.smileyRect = smiley.getRectF();
        this.leftEye = new Eye(smileyRect.left + smiley.getRadius() / 2, smileyRect.top + smiley.getRadius() / 2, smiley.getRadius());
        this.rightEye = new Eye(smileyRect.right - smiley.getRadius() / 2, smileyRect.top + smiley.getRadius() / 2, smiley.getRadius());
    }

    public Eye getLeftEye() {
        return this.leftEye;
    }

    public Eye getRightEye() {
        return this.rightEye;
    }
}
