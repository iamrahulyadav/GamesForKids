package com.nani.gamesForKids.Games.Animals;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalViewOnClickListener implements View.OnTouchListener {
    int soundResourceId;
    Context context;
    MediaPlayer mediaPlayer;
    private boolean isMediaPlayerActive = false;

    public AnimalViewOnClickListener(int soundResourceId, Context context) {
        this.soundResourceId = soundResourceId;
        this.context = context;

        setupMediaPlayer();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (!isMediaPlayerActive) {
            setupMediaPlayer();
        }

        if (event.getAction()==MotionEvent.ACTION_MOVE || event.getAction()==MotionEvent.ACTION_DOWN) {

            if (this.mediaPlayer.isPlaying()){
                return true;
            }

            this.mediaPlayer.start();
        }

        return true;
    }

    private void setupMediaPlayer() {
        this.mediaPlayer = MediaPlayer.create(this.context, this.soundResourceId);
        this.isMediaPlayerActive = true;

        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                AnimalViewOnClickListener.this.isMediaPlayerActive = false;
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
    }
}
