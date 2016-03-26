package com.nani.gamesForKids.Games.Animals.Basic;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.R;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

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

        Observable animalObservable = Observable.from(animals);
        animalObservable.subscribe(new Action1<Animal>() {

            @Override
            public void call(Animal animal) {
                displayAnimal(animal);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void displayAnimal(final Animal animal) {
        final ImageView imageView = (ImageView) findViewById(animal.getImageViewResource());

        Observable.create(subscriber -> {
            Glide.with(this).load(animal.getImageResource()).into(imageView);

            subscriber.onCompleted();
            subscriber.unsubscribe();
        }).subscribe();

        AnimalViewOnClickListener animalViewOnClickListener = new AnimalViewOnClickListener(animal.getSoundResource(), this);
        imageView.setOnTouchListener(animalViewOnClickListener);
    }
}
