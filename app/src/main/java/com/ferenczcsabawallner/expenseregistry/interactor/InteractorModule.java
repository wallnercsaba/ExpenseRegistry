package com.ferenczcsabawallner.expenseregistry.interactor;

import com.ferenczcsabawallner.expenseregistry.interactor.expenses.ExpensesInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 04. 15..
 */
@Module
public class InteractorModule {
    @Provides
    public ExpensesInteractor provideExpensesInteractor(){return  new ExpensesInteractor();}
}
