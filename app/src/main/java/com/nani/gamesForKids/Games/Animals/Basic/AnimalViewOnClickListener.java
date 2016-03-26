package com.nani.gamesForKids.Games.Animals.Basic;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalViewOnClickListener implements View.OnTouchListener {
    private int soundResourceId;
    private Context context;
    private MediaPlayer mediaPlayer;
    private boolean isMediaPlayerActive = false;

    public AnimalViewOnClickListener(int soundResourceId, Context context) {
        this.soundResourceId = soundResourceId;
        this.context = context;

        setupMediaPlayer();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if (!this.isMediaPlayerActive) {
            setupMediaPlayer();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {

            if (this.mediaPlayer.isPlaying()) {
                return true;
            }

            this.mediaPlayer.start();
        }

        return true;
    }

    private void setupMediaPlayer() {
        this.mediaPlayer = MediaPlayer.create(this.context, this.soundResourceId);
        this.isMediaPlayerActive = true;

        this.mediaPlayer.setOnCompletionListener(mediaPlayer -> {
            stopMediaPlayer();
        });
    }

    private void stopMediaPlayer() {
        this.isMediaPlayerActive = false;
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
