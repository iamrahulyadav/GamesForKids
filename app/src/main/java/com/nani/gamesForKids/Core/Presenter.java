package com.nani.gamesForKids.Core;

/**
 * Created by nataliajastrzebska on 15/03/16.
 */
public interface Presenter<V> {
    void attachView(V view);

    void start();

    void stop();
}
