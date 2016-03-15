package com.nani.gamesForKids.SmileyGame.Helpers;

import android.graphics.RectF;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class Eye {
    private float eyeCenterX;
    private float eyeCenterY;
    private float smileyRadius;

    float horizontalWhiteRadius;
    float verticalWhiteRadius;

    public Eye(float centerX, float centerY, float smileyRadius) {
        this.eyeCenterX = centerX;
        this.eyeCenterY = centerY;
        this.smileyRadius = smileyRadius;

        this.horizontalWhiteRadius = this.smileyRadius / 8;
        this.verticalWhiteRadius = this.smileyRadius / 4;
    }

    public RectF getOval() {
        return new RectF(
                this.eyeCenterX - this.horizontalWhiteRadius,
                this.eyeCenterY - this.verticalWhiteRadius,
                this.eyeCenterX + this.horizontalWhiteRadius,
                this.eyeCenterY + this.verticalWhiteRadius
        );
    }

    public RectF getIrisOval() {
        float horizontalIrisRadius = this.horizontalWhiteRadius / 1.5f;
        float verticalIrisRadius = this.verticalWhiteRadius / 1.3f;

        return new RectF(
                this.eyeCenterX - horizontalIrisRadius,
                this.eyeCenterY - verticalIrisRadius / 3,
                this.eyeCenterX + horizontalIrisRadius,
                this.eyeCenterY + verticalIrisRadius
        );
    }

    public float getIrisCenterX() {
        return this.eyeCenterX;
    }

    public float getIrisCenterY() {
        return this.eyeCenterY + this.smileyRadius / 10;
    }

    public float getIrisRadius() {
        return this.smileyRadius / 12;
    }
}
