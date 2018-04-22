package com.ferenczcsabawallner.expenseregistry;

import com.ferenczcsabawallner.expenseregistry.interactor.InteractorModule;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.ExpensesInteractor;
import com.ferenczcsabawallner.expenseregistry.network.NetworkModule;
import com.ferenczcsabawallner.expenseregistry.ui.UIModule;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 12..
 */

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class, NetworkModule.class})
public interface ExpenseRegistryApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(ExpensesInteractor expensesInteractor);
}
