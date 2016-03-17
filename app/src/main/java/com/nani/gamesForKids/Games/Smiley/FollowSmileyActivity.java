package com.nani.gamesForKids.Games.Smiley;

import android.content.Context;
import android.os.Bundle;

import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;
import com.nani.gamesForKids.Games.Smiley.Helpers.Smiley;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FollowSmileyActivity extends FullScreenGameActivity implements SmileyBoardView.BoardViewSizeChangedListener, FollowSmileyPresenter.FollowSmileyView {

    @Bind(R.id.boardView)
    SmileyBoardView boardView;

    FollowSmileyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_follow_smiley);
        ButterKnife.bind(this);

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
