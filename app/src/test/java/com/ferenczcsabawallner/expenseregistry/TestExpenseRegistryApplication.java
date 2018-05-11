package com.ferenczcsabawallner.expenseregistry;

import android.app.Application;

import com.ferenczcsabawallner.expenseregistry.repository.Repository;
import com.ferenczcsabawallner.expenseregistry.ui.UIModule;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 05. 09..
 */

public class TestExpenseRegistryApplication extends ExpenseRegistryApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerTestComponent.builder().testModule(new TestModule(this)).build();
    }
}
