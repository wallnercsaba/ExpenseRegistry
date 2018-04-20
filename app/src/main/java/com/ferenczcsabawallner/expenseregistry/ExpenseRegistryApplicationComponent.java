package com.ferenczcsabawallner.expenseregistry;

import com.ferenczcsabawallner.expenseregistry.interactor.expense.ExpenseRepositoryInteractor;
import com.ferenczcsabawallner.expenseregistry.repository.RepositoryModule;
import com.ferenczcsabawallner.expenseregistry.ui.UIModule;
import com.ferenczcsabawallner.expenseregistry.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Csabi on 2018. 04. 12..
 */

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class})
public interface ExpenseRegistryApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(ExpenseRepositoryInteractor expenseReposítoryInteractor);
}
