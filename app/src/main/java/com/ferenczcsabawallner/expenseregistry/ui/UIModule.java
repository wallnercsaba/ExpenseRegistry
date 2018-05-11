package com.ferenczcsabawallner.expenseregistry.ui;

import com.ferenczcsabawallner.expenseregistry.di.Network;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialogPresenter;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainPresenter;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    @Provides
    @Singleton
    public ExpenseEditDialogPresenter provideExpenseEditDialogPresenter(){return new ExpenseEditDialogPresenter();}


    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }
    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
