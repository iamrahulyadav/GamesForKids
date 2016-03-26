package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.MVPView;
import com.nani.gamesForKids.Core.Presenter;
import com.nani.gamesForKids.R;

import java.util.List;

/**
 * Created by nataliajastrzebska on 26/03/16.
 */
public class MovingAnimalsPresenter implements Presenter, View.OnTouchListener {

    private MovingAnimalsView movingAnimalsView;
    private float boardViewWidth, boardViewHeight;
    private Animal currentAnimal;
    private List<Animal> animals;
    private int animalIndex = 0;
    private MediaPlayer mediaPlayer;
    private boolean isMediaPlayerActive;
    private Bitmap backgroundBitmap;

    @Override
    public void attachView(Object view) {
        movingAnimalsView = (MovingAnimalsView) view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void boardViewSizeChanged(float boardViewWidth, float boardViewHeight) {
        this.boardViewWidth = boardViewWidth;
        this.boardViewHeight = boardViewHeight;

        createBackground(boardViewWidth, boardViewHeight);
        this.movingAnimalsView.setupCloud();

        AnimalsHelper animalsHelper = new AnimalsHelper(boardViewWidth, boardViewHeight);
        this.animals = animalsHelper.getAnimals();
        displayNewAnimal();
    }

    public void animalOutsideScreen() {
        stopMediaPlayer();
        displayNewAnimal();
    }

    private void displayNewAnimal() {
        currentAnimal = this.animals.get(this.animalIndex);
        this.movingAnimalsView.displayAnimal(this.currentAnimal);
        this.movingAnimalsView.setCloudText(this.currentAnimal.getAnimalSays());
        this.animalIndex++;

        if (this.animalIndex == animals.size()) {
            this.animalIndex = 0;
        }
    }

    private void createBackground(float width, float height) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    backgroundBitmap = Glide.with(MovingAnimalsPresenter.this.movingAnimalsView.getContext())
                            .load(R.drawable.animal_background_meadow).asBitmap()
                            .override((int) width, (int) height).centerCrop().into((int) width, (int) height).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void dummy) {
                MovingAnimalsPresenter.this.movingAnimalsView.setBackground(backgroundBitmap);
            }
        }.execute();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (isMotionEventClicked(motionEvent) && clickedOnAnimal(motionEvent)) {
            playSound();
        }

        return true;
    }

    private boolean isMotionEventClicked(MotionEvent motionEvent) {
        return isDown(motionEvent) || motionEvent.getAction() == MotionEvent.ACTION_MOVE;
    }

    private boolean isDown(MotionEvent motionEvent) {
        return motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_POINTER_DOWN;
    }

    private boolean clickedOnAnimal(MotionEvent motionEvent) {

        for (int i = 0; i < motionEvent.getPointerCount(); i++) {

            if (this.currentAnimal.getRectF().contains(motionEvent.getX(i), motionEvent.getY(i))) {
                return true;
            }
        }

        return false;
    }

    private void playSound() {

        if (!this.isMediaPlayerActive) {
            setupMediaPlayer();
        }

        if (!this.mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            this.movingAnimalsView.showCloud();
        }
    }

    private void setupMediaPlayer() {
        this.mediaPlayer = MediaPlayer.create(this.movingAnimalsView.getContext(), this.currentAnimal.getSoundResource());
        this.isMediaPlayerActive = true;

        this.mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            stopMediaPlayer();
        });
    }

    private void stopMediaPlayer() {

        if (this.isMediaPlayerActive) {
            this.isMediaPlayerActive = false;
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.movingAnimalsView.hideCloud();
        }
    }

    public interface MovingAnimalsView extends MVPView {

        void displayAnimal(Animal animal);

        void setCloudText(int animalSaysResource);

        void setBackground(Bitmap bitmap);

        void setupCloud();

        void showCloud();

        void hideCloud();
    }
}
