package com.nani.gamesForKids.Games.Smiley.Helpers;

import android.graphics.RectF;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class Smiley {
    private float centerX;
    private float centerY;
    private float radius;
    private int color;

    RectF rectF;

    public Smiley() {
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public RectF getRectF() {

        if (rectF == null) {
            setupRectf();
        }

        return rectF;
    }

    private void setupRectf() {
        this.rectF = new RectF(
                this.getCenterX() - this.getRadius(),
                this.getCenterY() - this.getRadius(),
                this.getCenterX() + this.getRadius(),
                this.getCenterY() + this.getRadius());
    }
}
