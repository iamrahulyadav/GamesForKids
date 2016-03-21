package com.nani.gamesForKids.Games.Animals;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;
import java.util.List;

/**
 * Created by nataliajastrzebska on 21/03/16.
 */
public class AnimalsActivity extends FullScreenGameActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        AnimalsHelper animalHelper = new AnimalsHelper();
        List<Animal> animals = animalHelper.getAnimals();

        for (Animal animal : animals) {
            displayAnimal(animal);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void displayAnimal(Animal animal) {
        ImageView imageView = (ImageView) findViewById(animal.getImageViewResource());
        Glide.with(this).load(animal.getImageResource()).into(imageView);

        AnimalViewOnClickListener animalViewOnClickListener = new AnimalViewOnClickListener(animal.getSoundResource(), this);
        imageView.setOnTouchListener(animalViewOnClickListener);
    }
}
