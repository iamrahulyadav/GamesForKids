package com.nani.gamesForKids.Games.Animals;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class Animal {
    private int nameResource;
    private int imageResource;
    private int imageViewResource;
    private int soundResource;

    public Animal(int nameResource, int imageResource, int imageViewResource, int soundResource) {
        this.nameResource = nameResource;
        this.imageResource = imageResource;
        this.imageViewResource = imageViewResource;
        this.soundResource = soundResource;
    }

    public int getNameResource() {
        return nameResource;
    }

    public void setNameResource(int nameResource) {
        this.nameResource = nameResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getImageViewResource() {
        return imageViewResource;
    }

    public void setImageViewResource(int imageViewResource) {
        this.imageViewResource = imageViewResource;
    }

    public int getSoundResource() {
        return soundResource;
    }

    public void setSoundResource(int soundResource) {
        this.soundResource = soundResource;
    }
}
