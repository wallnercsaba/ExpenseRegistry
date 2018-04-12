package com.ferenczcsabawallner.expenseregistry;

import android.app.Application;

/**
 * Created by Csabi on 2018. 04. 12..
 */

public class ExpenseRegistryApplication extends Application {

    public static MainAppComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerMainAppComponent.builder().build();
    }
}
