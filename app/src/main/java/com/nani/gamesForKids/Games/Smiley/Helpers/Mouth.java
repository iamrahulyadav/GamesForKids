package com.nani.gamesForKids.Games.Smiley.Helpers;

import android.graphics.RectF;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class Mouth {
    RectF smileyRectf;
    float smileyRadius;

    public Mouth(Smiley smiley) {
        this.smileyRectf = smiley.getRectF();
        this.smileyRadius = smiley.getRadius();
    }

    public RectF getOval() {

        return new RectF(
                this.smileyRectf.left + this.smileyRadius /5,
                this.smileyRectf.top + this.smileyRadius /5,
                this.smileyRectf.right - this.smileyRadius /5,
                this.smileyRectf.bottom - this.smileyRadius /5
        );
    }
}
