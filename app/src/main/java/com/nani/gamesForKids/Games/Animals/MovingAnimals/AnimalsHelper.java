package com.nani.gamesForKids.Games.Animals.MovingAnimals;

import com.nani.gamesForKids.Games.Animals.MovingAnimals.Animal;
import com.nani.gamesForKids.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalsHelper {
    private List<Animal> animals;

    public AnimalsHelper(float boardViewWidth, float boardViewHeight) {
        this.animals = new ArrayList<>();
        populateAnimalList(boardViewWidth, boardViewHeight);
    }

    public void populateAnimalList(float boardViewWidth, float boardViewHeight) {
        this.animals.add(new Animal(R.drawable.animal_cat, R.raw.animal_cat, R.string.cat_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_cow, R.raw.animal_cow, R.string.cow_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_dog, R.raw.animal_dog, R.string.dog_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_goat, R.raw.animal_goat, R.string.goat_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_duck, R.raw.animal_duck, R.string.duck_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_frog, R.raw.animal_frog, R.string.frog_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_sheep, R.raw.animal_sheep, R.string.sheep_says, boardViewWidth, boardViewHeight));
        this.animals.add(new Animal(R.drawable.animal_horse, R.raw.animal_horse, R.string.horse_says, boardViewWidth, boardViewHeight));
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }
}
