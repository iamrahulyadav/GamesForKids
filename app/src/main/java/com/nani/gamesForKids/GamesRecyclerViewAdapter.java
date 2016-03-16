package com.nani.gamesForKids;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 16/03/16.
 */
public class GamesRecyclerViewAdapter extends RecyclerView.Adapter<GamesRecyclerViewAdapter.GameViewHolder> {

    private List<Game> games;

    public GamesRecyclerViewAdapter() {
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
        holder.iconImageView.setImageResource(game.getGameImageResource());
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

        this.games.add(new Game("Smiley", R.mipmap.ic_launcher));
        this.games.add(new Game("Smiley", R.mipmap.ic_launcher, "info"));
        this.games.add(new Game("Smiley", R.mipmap.ic_launcher));
        this.games.add(new Game("Smiley", R.mipmap.ic_launcher, "info"));
    }
}


