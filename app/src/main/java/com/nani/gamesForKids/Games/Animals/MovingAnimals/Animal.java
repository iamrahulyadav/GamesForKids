package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;


/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class Animal {
    private int imageResource;
    private int soundResource;
    private int animalSays;
    private Bitmap bitmap;
    private float x, y;
    public boolean isReadyToDraw = false;
    float boardWidth, boardHeight;

    public Animal(int imageResource, int soundResource, int animalSays, float boardWidth, float boardHeight) {
        this.imageResource = imageResource;
        this.soundResource = soundResource;
        this.animalSays = animalSays;

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getSoundResource() {
        return soundResource;
    }

    public void setSoundResource(int soundResource) {
        this.soundResource = soundResource;
    }

    public void start() {
        this.x = this.boardWidth + 20.0f;

        if (this.bitmap == null) {
            this.y = 100.f;
        } else {
            setY();
        }
    }

    public int getAnimalSays() {
        return animalSays;
    }

    public void setAnimalSays(int animalSays) {
        this.animalSays = animalSays;
    }

    private void setY() {
        this.y = this.boardHeight - this.bitmap.getHeight();
    }


    public void update(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Bitmap getBitmap(Context context) {

        if (this.bitmap == null) {
            retrieveBitmap(context);
        }

        return this.bitmap;
    }

    public RectF getRectF() {

        if (this.bitmap == null) {
            return new RectF(0, 0, 0, 0);
        } else {
            return new RectF(this.x, this.y, this.x + this.bitmap.getWidth(), this.y + this.bitmap.getHeight());
        }
    }

    private void retrieveBitmap(Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    bitmap = Glide.with(context).load(getImageResource()).asBitmap().into(300, 300).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void dummy) {

                if (bitmap != null) {
                    setY();
                    isReadyToDraw = true;
                }
            }
        }.execute();
    }

    public boolean isOutsideScreen() {

        if (this.x < 0) {
            return true;
        }

        return false;
    }
}
