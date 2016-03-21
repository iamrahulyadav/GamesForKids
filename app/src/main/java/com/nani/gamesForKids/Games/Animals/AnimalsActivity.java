package com.nani.gamesForKids.Games.Animals;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalsActivity extends FullScreenGameActivity {

    @Bind(R.id.cowImageView)
    ImageView cowImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animals);
        ButterKnife.bind(this);

        Glide.with(this).load(R.drawable.animal_cow).into(cowImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @OnClick(R.id.cowImageView)
    public void onCowClicked() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cow);
        mediaPlayer.start();
    }
}
