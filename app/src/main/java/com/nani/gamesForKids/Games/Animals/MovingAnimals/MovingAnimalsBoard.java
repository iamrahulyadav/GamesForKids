package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by nataliajastrzebska on 22/03/16.
 */
public class MovingAnimalsBoard extends ImageView implements Runnable {

    private Thread thread = null;
    private Boolean shouldDraw = false;
    private Animal animal;
    private Context context;
    private Bitmap backgroundBitmap;

    public MovingAnimalsBoard(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (this.backgroundBitmap != null) {
            canvas.drawBitmap(this.backgroundBitmap, 0, 0, paint);
        }

        if (this.animal == null) {
            return;
        }

        if (!this.animal.isReadyToDraw) {
            this.animal.getBitmap(context);

            return;
        }

        canvas.drawBitmap(this.animal.getBitmap(context), this.animal.getX(), this.animal.getY(), paint);
    }

    @Override
    public void run() {

        while (shouldDraw) {

            if (this.animal == null) {
                continue;
            }

            this.animal.update(-0.005f, 0);
            ((MovingAnimalsActivity)this.context).runOnUiThread(() -> invalidate());

            if (this.animal.isOutsideScreen()) {
                ((BoardViewListener) this.context).onAnimalOutsideScreen();
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        ((BoardViewListener) this.context).onSizeChanged();
    }

    public void onResume() {
        this.thread = new Thread(this);
        this.thread.start();

        this.shouldDraw = true;
    }

    public void onPause() {
        this.shouldDraw = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.thread = null;
        }
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        animal.start();
        this.animal = animal;
    }

    public void setBackgroundBitmap(Bitmap backgroundBitmap) {
        this.backgroundBitmap = backgroundBitmap;
    }

    interface BoardViewListener {
        void onSizeChanged();

        void onAnimalOutsideScreen();
    }
}
