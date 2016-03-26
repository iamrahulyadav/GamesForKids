package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class MovingAnimalsActivity extends FullScreenGameActivity implements MovingAnimalsBoard.BoardViewListener, View.OnTouchListener {

    @Bind(R.id.boardView)
    MovingAnimalsBoard boardView;
    @Bind(R.id.cloudRelativeLayout)
    LinearLayout cloudRelativeLayout;
    @Bind(R.id.cloudImageView)
    ImageView cloudImageView;
    @Bind(R.id.cloudTextView)
    TextView cloudTextView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private List<Animal> animals;
    private int animalIndex = 0;
    private MediaPlayer mediaPlayer;
    private boolean isMediaPlayerActive;
    private Bitmap backgroundBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_animals);
        ButterKnife.bind(this);

        boardView.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.boardView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.boardView.onPause();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void displayAnimal() {
        this.boardView.setAnimal(this.animals.get(this.animalIndex));
        setCloud();
        this.animalIndex++;

        if (this.animalIndex == animals.size()) {
            this.animalIndex = 0;
        }
    }

    @Override
    public void onSizeChanged() {

        if (this.boardView == null) {
            return;
        }

        createBackground(this.boardView.getWidth(), this.boardView.getHeight());
        setupCloud();

        AnimalsHelper animalsHelper = new AnimalsHelper(boardView.getWidth(), boardView.getHeight());
        this.animals = animalsHelper.getAnimals();
        displayAnimal();
    }

    private void createBackground(int width, int height) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    backgroundBitmap = Glide.with(MovingAnimalsActivity.this)
                            .load(R.drawable.animal_background_meadow).asBitmap()
                            .override(width, height).centerCrop().into(width, height).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void dummy) {
                MovingAnimalsActivity.this.boardView.setBackgroundBitmap(backgroundBitmap);
                progressBar.setVisibility(View.GONE);
            }
        }.execute();
    }

    @Override
    public void onAnimalOutsideScreen() {
        stopMediaPlayer();
        displayAnimal();
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

            if (this.boardView.getAnimal().getRectF().contains(motionEvent.getX(i), motionEvent.getY(i))) {
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
            showCloud();
        }
    }

    private void setupMediaPlayer() {
        this.mediaPlayer = MediaPlayer.create(this, this.boardView.getAnimal().getSoundResource());
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
            hideCloud();
        }
    }

    private void showCloud() {
        runOnUiThread(() -> this.cloudRelativeLayout.setVisibility(View.VISIBLE));
    }

    private void hideCloud() {
        runOnUiThread(() -> this.cloudRelativeLayout.setVisibility(View.GONE));
    }

    private void setupCloud() {
        Glide.with(this).load(R.drawable.helper_cloud).into(this.cloudImageView);
    }

    private void setCloud() {
        runOnUiThread(() ->this.cloudTextView.setText(this.boardView.getAnimal().getAnimalSays()));
    }
}
