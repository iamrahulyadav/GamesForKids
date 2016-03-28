package com.nani.gamesForKids.Games.AbcHome

import android.graphics.Color
import android.graphics.PointF

/**
 * Created by nataliajastrzebska on 26/03/16.
 */

class LetterCirclesHelper {
    var points: MutableList<PointF> = arrayListOf()
    var letterCircles: MutableList<LetterCircle> = arrayListOf()
    var radius: Float = 0.0f;

    constructor(points: MutableList<PointF>, radius: Float) {
        this.points = points
        this.radius = radius

        populateList()
    }

    fun populateList() {
        letterCircles.clear()

        points.forEachIndexed { i, pointF ->
            letterCircles.add(LetterCircle(pointF.x, pointF.y, radius, (i + 65).toChar(), Color.RED))
        }

    }
}