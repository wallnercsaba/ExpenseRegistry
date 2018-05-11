package com.ferenczcsabawallner.expenseregistry;

import android.app.Application;

import com.ferenczcsabawallner.expenseregistry.repository.Repository;
import com.ferenczcsabawallner.expenseregistry.ui.UIModule;
import com.orm.SugarApp;
import com.orm.SugarContext;

import javax.inject.Inject;

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
