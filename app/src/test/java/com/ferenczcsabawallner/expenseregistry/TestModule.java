package com.ferenczcsabawallner.expenseregistry;

import android.content.Context;

import com.ferenczcsabawallner.expenseregistry.di.Network;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialogPresenter;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainPresenter;
import com.ferenczcsabawallner.expenseregistry.utils.UiExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 04. 20..
 */
@Module
public class TestModule {
    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter(){return new MainPresenter();}

    @Provides
    @Singleton
    public ExpenseEditDialogPresenter provideExpenseEditDialogPresenter(){return new ExpenseEditDialogPresenter();}

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }
}
