package com.nani.gamesForKids.Games.AbcHome

import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF

/**
 * Created by nataliajastrzebska on 26/03/16.
 */

class Home(boardWidth: Float, boardHeight: Float, radius: Float) {
    var margin: Float = 16.0f
    var houseMargin: Float = margin + radius
    var points: MutableList<PointF> = arrayListOf()

    var roofBottom: Float = boardHeight / 2.5f
    var roofHorizontalMiddle: Float = boardWidth / 2.0f
    var roofTop: Float = houseMargin * 3
    var houseLeft: Float = houseMargin
    var houseRight: Float = boardWidth - houseMargin
    var houseBottom: Float = boardHeight - houseMargin * 3

    var doorLeft: Float = houseLeft + margin
    var doorTop: Float = roofBottom + (houseBottom - roofBottom) / 2.5f
    var doorRight: Float = houseLeft + (houseRight - houseLeft) / 2.1f

    var windowLeft: Float = doorRight + margin
    var windowRight: Float = houseRight - margin
    var windowTop: Float = roofBottom + margin
    var windowBottom: Float = doorTop + margin

    fun getList(): MutableList<PointF> {
        points.clear()
        points.add(PointF(houseLeft, roofBottom))
        points.add(PointF(roofHorizontalMiddle, roofTop))
        points.add(PointF(houseRight, roofBottom))
        points.add(PointF(houseLeft, roofBottom))
        points.add(PointF(houseLeft, houseBottom))
        points.add(PointF(houseRight, houseBottom))
        points.add(PointF(houseRight, roofBottom))

        return points;
    }

    fun getRoofPath(): Path {
        var path: Path = Path()
        path.moveTo(points[0].x, points[0].y)
        path.lineTo(points[1].x, points[1].y)
        path.lineTo(points[2].x, points[2].y)
        path.lineTo(points[0].x, points[0].y)

        return path
    }

    fun getHousePath(): Path {
        var path: Path = Path()
        path.moveTo(points[0].x, points[0].y)
        path.lineTo(points[4].x, points[4].y)
        path.lineTo(points[5].x, points[5].y)
        path.lineTo(points[6].x, points[6].y)
        path.lineTo(points[0].x, points[0].y)

        return path
    }

    fun getDoorRect(): RectF {
        return RectF(doorLeft + margin, doorTop, doorRight - margin, houseBottom)
    }

    fun getWindowRect(): RectF {
        return RectF(windowLeft, windowTop, windowRight, windowBottom)
    }

    fun getWindowBorderPath(): Path {
        var windowHorizontalMiddle: Float = windowLeft + (windowRight - windowLeft) / 2.0f
        var windowVerticalMiddle: Float = windowTop + (windowBottom - windowTop) / 2.0f

        var path: Path = Path()
        path.moveTo(windowLeft, windowTop)
        path.lineTo(windowRight, windowTop)
        path.lineTo(windowRight, windowBottom)
        path.lineTo(windowLeft, windowBottom)
        path.lineTo(windowLeft, windowTop)
        path.moveTo(windowHorizontalMiddle, windowTop)
        path.lineTo(windowHorizontalMiddle, windowBottom)
        path.moveTo(windowLeft, windowVerticalMiddle)
        path.lineTo(windowRight, windowVerticalMiddle)

        return  path
    }
}
