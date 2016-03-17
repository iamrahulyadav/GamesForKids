package com.nani.gamesForKids.GamesList;

/**
 * Created by nataliajastrzebska on 16/03/16.
 */
public class Game {
    private String name;
    private int gameImageResource;
    private String information;
    private Class activityClass;

    public Game(String name, int gameImage, Class activityClass) {
        this.name = name;
        this.gameImageResource = gameImage;
        this.activityClass = activityClass;
    }

    public Game(String name, int gameImage, String information, Class activityClass) {
        this.name = name;
        this.gameImageResource = gameImage;
        this.information = information;
        this.activityClass = activityClass;
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

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }
}
