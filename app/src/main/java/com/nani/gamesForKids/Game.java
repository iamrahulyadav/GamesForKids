package com.nani.gamesForKids;

/**
 * Created by nataliajastrzebska on 16/03/16.
 */
public class Game {
    private String name;
    private int gameImageResource;
    private String information;

    public Game(String name, int gameImage) {
        this.name = name;
        this.gameImageResource = gameImage;
    }

    public Game(String name, int gameImage, String information) {
        this.name = name;
        this.gameImageResource = gameImage;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGameImageResource() {
        return gameImageResource;
    }

    public void setGameImageResource(int gameImageResource) {
        this.gameImageResource = gameImageResource;
    }

    public boolean hasInformation() {
        return this.information == null;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
