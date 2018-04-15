package com.ferenczcsabawallner.expenseregistry;

import com.ferenczcsabawallner.expenseregistry.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 12..
 */

@Singleton
@Component(modules = {})
public interface ExpenseRegistryApplicationComponent {
    void inject(MainActivity mainActivity);
}
