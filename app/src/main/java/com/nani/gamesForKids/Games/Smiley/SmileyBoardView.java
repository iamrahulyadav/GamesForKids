package com.nani.gamesForKids.Games.Smiley;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.nani.gamesForKids.Games.Smiley.Helpers.Eye;
import com.nani.gamesForKids.Games.Smiley.Helpers.Eyes;
import com.nani.gamesForKids.Games.Smiley.Helpers.Mouth;
import com.nani.gamesForKids.Games.Smiley.Helpers.Smiley;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class SmileyBoardView extends ImageView {

    private Smiley smiley;
    private BoardViewSizeChangedListener boardViewSizeChangedListener;

    public SmileyBoardView(Context context) {
        super(context);

        this.boardViewSizeChangedListener = (BoardViewSizeChangedListener) context;
    }


    public SmileyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.boardViewSizeChangedListener = (BoardViewSizeChangedListener) context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (this.smiley != null) {
            drawSmiley(canvas);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.boardViewSizeChangedListener.onSizeChanged();
    }

    public void setSmiley(Smiley smiley) {
        this.smiley = smiley;
    }

    private void drawSmiley(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(this.smiley.getColor());
        canvas.drawCircle(this.smiley.getCenterX(), this.smiley.getCenterY(), this.smiley.getRadius(), paint);

        drawFace(canvas);
    }

    private void drawFace(Canvas canvas) {
        drawMouth(canvas);
        drawEyes(canvas);
    }

    private void drawMouth(Canvas canvas) {
        Mouth mouth = new Mouth(this.smiley);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        canvas.drawArc(mouth.getOval(), 5, 165, false, paint);
    }

    private void drawEyes(Canvas canvas) {
        Eyes eyes = new Eyes(this.smiley);

        drawEye(canvas, eyes.getLeftEye());
        drawEye(canvas, eyes.getRightEye());
    }

    private void drawEye(Canvas canvas, Eye eye) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        drawWhiteOfEye(canvas, paint, eye);
        drawWhiteOfEyeBorder(canvas, paint, eye);
        drawIrisOfEye(canvas, paint, eye);
    }

    private void drawWhiteOfEye(Canvas canvas, Paint paint, Eye eye) {
        paint.setColor(Color.WHITE);
        canvas.drawOval(eye.getOval(), paint);
    }

    private void drawWhiteOfEyeBorder(Canvas canvas, Paint paint, Eye eye) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawOval(eye.getOval(), paint);
    }

    private void drawIrisOfEye(Canvas canvas, Paint paint, Eye eye) {
        paint.setColor(this.smiley.getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(eye.getIrisOval(), paint);
    }

    public interface BoardViewSizeChangedListener {
        void onSizeChanged();
    }
}
