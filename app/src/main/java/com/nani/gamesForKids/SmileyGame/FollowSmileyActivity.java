package com.nani.gamesForKids.SmileyGame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nani.gamesForKids.R;
import com.nani.gamesForKids.SmileyGame.Helpers.Smiley;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FollowSmileyActivity extends AppCompatActivity implements SmileyBoardView.BoardViewSizeChangedListener, FollowSmileyPresenter.FollowSmileyView {

    @Bind(R.id.boardView)
    SmileyBoardView boardView;

    private View mDecorView;
    FollowSmileyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_smiley);
        ButterKnife.bind(this);

        this.mDecorView = getWindow().getDecorView();

        this.presenter = new FollowSmileyPresenter();
        this.presenter.attachView(this);
        this.presenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    @Override
    public void onSizeChanged() {
        this.presenter.setSizeOfView(this.boardView.getWidth(), this.boardView.getHeight());
        this.boardView.setOnTouchListener(this.presenter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setSmileyForView(Smiley smiley) {
        this.boardView.setSmiley(smiley);
        this.boardView.invalidate();
    }

    @Override
    public void setBackgroundColor(int color) {
        this.boardView.setBackgroundColor(color);
    }
}
