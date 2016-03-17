package com.nani.gamesForKids.GamesList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Games.CatchADot.CatchDotActivity;
import com.nani.gamesForKids.R;
import com.nani.gamesForKids.Games.Smiley.FollowSmileyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 16/03/16.
 */
public class GamesRecyclerViewAdapter extends RecyclerView.Adapter<GamesRecyclerViewAdapter.GameViewHolder> {

    private List<Game> games;
    private Context context;

    public GamesRecyclerViewAdapter(Context context) {
        this.context = context;

        populateListOfGames();
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_games, viewGroup, false);

        return new GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = this.games.get(position);
        holder.nameTextView.setText(game.getName());
        Glide.with(this.context).load(game.getGameImageResource()).into(holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.gameNameTextView)
        TextView nameTextView;
        @Bind(R.id.gameIconImageView)
        ImageView iconImageView;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void populateListOfGames() {
        this.games = new ArrayList<>();

        this.games.add(new Game("Smiley", R.drawable.game_smiley, FollowSmileyActivity.class));
        this.games.add(new Game("Catch a dot", R.mipmap.ic_launcher, CatchDotActivity.class));
        this.games.add(new Game("Smiley", R.drawable.game_smiley, FollowSmileyActivity.class));
        this.games.add(new Game("Smiley", R.mipmap.ic_launcher, "info", FollowSmileyActivity.class));
    }

    public Game getGameAtPosition(int position) {
        return this.games.get(position);
    }
}


