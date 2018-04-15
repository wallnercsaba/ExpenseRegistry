package com.ferenczcsabawallner.expenseregistry.ui;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 04. 15..
 */

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
