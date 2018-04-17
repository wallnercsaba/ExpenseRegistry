package com.ferenczcsabawallner.expenseregistry;

import android.app.Application;

import com.ferenczcsabawallner.expenseregistry.ui.UIModule;

/**
 * Created by Csabi on 2018. 04. 12..
 */

public class ExpenseRegistryApplication extends Application {

    public static ExpenseRegistryApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerExpenseRegistryApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
