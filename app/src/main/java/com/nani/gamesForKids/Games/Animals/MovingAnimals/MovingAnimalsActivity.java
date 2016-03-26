package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class MovingAnimalsActivity extends FullScreenGameActivity implements MovingAnimalsBoard.BoardViewListener, MovingAnimalsPresenter.MovingAnimalsView {

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


    private MovingAnimalsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_animals);
        ButterKnife.bind(this);

        presenter = new MovingAnimalsPresenter();
        presenter.attachView(this);
        presenter.start();

        boardView.setOnTouchListener(this.presenter);
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

    @Override
    public void onSizeChanged() {

        if (this.boardView == null) {
            return;
        }

        this.presenter.boardViewSizeChanged(this.boardView.getWidth(), this.boardView.getHeight());
    }

    @Override
    public void onAnimalOutsideScreen() {
        this.presenter.animalOutsideScreen();
    }

    @Override
    public void displayAnimal(Animal animal) {
        this.boardView.setAnimal(animal);
    }

    @Override
    public void setCloudText(int animalSaysResource) {
        runOnUiThread(() -> this.cloudTextView.setText(getResources().getString(animalSaysResource)));
    }

    @Override
    public void setBackground(Bitmap bitmap) {

        if (this.boardView == null) {
            return;
        }

        this.boardView.setBackgroundBitmap(bitmap);
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setupCloud() {
        Glide.with(this).load(R.drawable.helper_cloud).into(this.cloudImageView);
    }

    @Override
    public void showCloud() {
        runOnUiThread(() -> this.cloudRelativeLayout.setVisibility(View.VISIBLE));
    }

    @Override
    public void hideCloud() {
        runOnUiThread(() -> this.cloudRelativeLayout.setVisibility(View.GONE));
    }

    @Override
    public Context getContext() {
        return this;
    }
}
