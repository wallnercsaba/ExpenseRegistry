package com.ferenczcsabawallner.expenseregistry.interactor;

import com.ferenczcsabawallner.expenseregistry.interactor.expense.ExpenseRepositoryInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Csabi on 2018. 04. 20..
 */
@Module
public class InteractorModule {
    @Provides
    public ExpenseRepositoryInteractor provideExpensesInteractor(){return  new ExpenseRepositoryInteractor();}
}
