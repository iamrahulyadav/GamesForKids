package com.nani.gamesForKids.Games.AbcHome

import android.graphics.Rect

/**
 * Created by nataliajastrzebska on 26/03/16.
 */
public class LetterCircle {
    var centerX: Float = 0.0f;
    var centerY: Float = 0.0f;
    var radius: Float = 0.0f;
    var letter: Char = ' ';
    var circleColor: Int = 0;

    constructor(centerX: Float, centerY: Float, radius: Float, letter: Char, circleColor: Int) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.letter = letter;
        this.circleColor = circleColor;
    }

    fun getRectForCircle(): Rect {
        return Rect(
                (centerX - radius).toInt(),
                (centerY - radius).toInt(),
                (centerX + radius).toInt(),
                (centerY + radius).toInt())
    }
}