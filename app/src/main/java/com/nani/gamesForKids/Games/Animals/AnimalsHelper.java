package com.nani.gamesForKids.Games.Animals;

import com.nani.gamesForKids.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalsHelper {
    private List<Animal> animals;

    public AnimalsHelper() {
        this.animals = new ArrayList<>();
        populateAnimalList();
    }

    public void populateAnimalList() {
        this.animals.add(new Animal(R.string.cow, R.drawable.animal_cow, R.id.cowImageView, R.raw.animal_cow));
        this.animals.add(new Animal(R.string.cat, R.drawable.animal_cat, R.id.catImageView, R.raw.animal_cat));
        this.animals.add(new Animal(R.string.dog, R.drawable.animal_dog, R.id.dogImageView, R.raw.animal_dog));
        this.animals.add(new Animal(R.string.horse, R.drawable.animal_horse, R.id.horseImageView, R.raw.animal_horse));
        this.animals.add(new Animal(R.string.duck, R.drawable.animal_duck, R.id.duckImageView, R.raw.animal_duck));
        this.animals.add(new Animal(R.string.sheep, R.drawable.animal_sheep, R.id.sheepImageView, R.raw.animal_sheep));
        this.animals.add(new Animal(R.string.goat, R.drawable.animal_goat, R.id.goatImageView, R.raw.animal_goat));
        this.animals.add(new Animal(R.string.frog, R.drawable.animal_frog, R.id.frogImageView, R.raw.animal_frog));
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }
}
