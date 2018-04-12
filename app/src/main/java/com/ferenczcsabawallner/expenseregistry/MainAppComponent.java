package com.ferenczcsabawallner.expenseregistry;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 12..
 */

@Singleton
@Component(modules = {})
public interface MainAppComponent {
    void inject(MainActivity mainActivity);
}
