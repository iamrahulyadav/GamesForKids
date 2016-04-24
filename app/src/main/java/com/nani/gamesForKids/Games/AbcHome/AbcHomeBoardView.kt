package com.nani.gamesForKids.Games.AbcHome

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView
import com.nani.gamesForKids.R

/**
 * Created by nataliajastrzebska on 26/03/16.
 */

public class AbcHomeBoardView : ImageView {
    var letterCircle: LetterCircle? = null
    var mainPath: Path = Path()
    var home: Home? = null
    var strokeWidth: Float = 10.0f

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawHome(canvas)
        drawMainPath(canvas)

        if (letterCircle != null) {
            drawCircle(canvas)
            drawLetter(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        (context as AbcBoardViewListener).boardViewSizeChanged();
    }

    fun setCircle(letterCircle: LetterCircle) {

        if (isThisFirstLetter()) {
            mainPath.moveTo(letterCircle.centerX, letterCircle.centerY)
        }

        this.letterCircle = letterCircle
        mainPath.lineTo(letterCircle.centerX, letterCircle.centerY)

        invalidate()
    }

    fun resetPath() {
        mainPath.reset()
        letterCircle = null
    }

    fun isThisFirstLetter(): Boolean {
        return letterCircle == null
    }

    fun drawCircle(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = letterCircle!!.circleColor

        canvas!!.drawCircle(letterCircle!!.centerX, letterCircle!!.centerY, letterCircle!!.radius, paint)
    }

    fun drawLetter(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.WHITE
        paint.textSize = letterCircle!!.radius * 2
        paint.textAlign = Paint.Align.CENTER;
        paint.getTextBounds(letterCircle!!.letter.toString(), 0, 1, letterCircle!!.getRectForCircle())

        canvas!!.drawText(letterCircle!!.letter.toString(), letterCircle!!.centerX, letterCircle!!.centerY + letterCircle!!.radius / 1.5f, paint)
    }

    fun drawMainPath(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.BLUE
        paint.strokeWidth = strokeWidth;
        paint.style = Paint.Style.STROKE

        canvas!!.drawPath(mainPath, paint)
    }

    fun drawHome(canvas: Canvas?) {

        if (home != null) {
            drawRoof(canvas)
            drawHouse(canvas)
            drawDoor(canvas)
            drawWindow(canvas)
        }
    }

    fun drawRoof(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.abc_home_roof)

        canvas!!.drawPath(home!!.getRoofPath(), paint)
    }

    fun drawHouse(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.abc_home_house)

        canvas!!.drawPath(home!!.getHousePath(), paint)
    }

    fun drawDoor(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.abc_home_door)
        canvas!!.drawRect(home!!.getDoorRect(), paint)

        paint.color = ContextCompat.getColor(context, R.color.abc_home_door_border)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        canvas!!.drawRect(home!!.getDoorRect(), paint)
    }

    fun drawWindow(canvas: Canvas?) {
        var paint: Paint = Paint()
        paint.isAntiAlias = true
        paint.color = ContextCompat.getColor(context, R.color.abc_home_window)
        canvas!!.drawRect(home!!.getWindowRect(), paint)

        paint.color = ContextCompat.getColor(context, R.color.abc_home_window_border)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        canvas!!.drawPath(home!!.getWindowBorderPath(), paint)
    }

    interface AbcBoardViewListener {
        fun boardViewSizeChanged()
    }
}
