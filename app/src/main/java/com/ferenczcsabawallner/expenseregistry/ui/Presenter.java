package com.ferenczcsabawallner.expenseregistry.ui;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public abstract class Presenter<S> {
    protected S screen;

    public void attachScreen(S screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}