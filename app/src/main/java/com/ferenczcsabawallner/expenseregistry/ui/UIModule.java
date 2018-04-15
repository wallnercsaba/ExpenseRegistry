package com.ferenczcsabawallner.expenseregistry.ui;

import com.ferenczcsabawallner.expenseregistry.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 04. 15..
 */

@Module
public class UIModule {
    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(){return new MainPresenter();}
}
