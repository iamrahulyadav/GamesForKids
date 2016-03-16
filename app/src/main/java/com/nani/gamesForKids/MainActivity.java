package com.nani.gamesForKids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.SmileyGame.FollowSmileyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    @Bind(R.id.gamesRecyclerView)
    RecyclerView gamesRecyclerView;
    @Bind(R.id.applicationTitleImageView)
    ImageView logoImageView;
    @Bind(R.id.backgroundImageView)
    ImageView backgroundImageView;

    private GamesRecyclerViewAdapter adapter;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecyclerViewLayoutManager();
        setupRecyclerViewAdapter();
        setupRecyclerViewClickListener();

        Glide.with(this).load(R.drawable.toddler_logo).into(this.logoImageView);
        Glide.with(this).load(R.drawable.background).centerCrop().into(this.backgroundImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }

    private void setupRecyclerViewLayoutManager() {
        int columns = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        this.gamesRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setupRecyclerViewAdapter() {
        this.adapter = new GamesRecyclerViewAdapter(this);

        this.gamesRecyclerView.setAdapter(this.adapter);
    }

    private void setupRecyclerViewClickListener() {
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.gamesRecyclerView.addOnItemTouchListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && gestureDetector.onTouchEvent(e)) {
            displayGameForPosition(recyclerView.getChildAdapterPosition(childView));

            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private void displayGameForPosition(int position) {
        startActivity(new Intent(this, FollowSmileyActivity.class));
    }
}
