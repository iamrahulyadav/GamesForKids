package com.nani.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public class BoardView extends ImageView {

    private Dot dot;
    private BoardViewSizeChangedListener boardViewSizeChangedListener;

    public BoardView(Context context) {
        super(context);

        this.boardViewSizeChangedListener = (BoardViewSizeChangedListener) context;
    }


    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.boardViewSizeChangedListener = (BoardViewSizeChangedListener) context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dot != null) {
            drawDot(canvas, dot);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.boardViewSizeChangedListener.onSizeChanged();
    }

    public Dot getDot() {
        return this.dot;
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    private void drawDot(Canvas c, Dot dot) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(dot.getColor());
        c.drawCircle(dot.getCenterX(), dot.getCenterY(), dot.getRadius(), paint);
    }

    public interface BoardViewSizeChangedListener {
        void onSizeChanged();
    }
}
