package com.ferenczcsabawallner.expenseregistry.ui.editDialog;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.di.Network;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.ExpensesInteractor;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.AddExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.DeleteExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.ModifyExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 05. 08..
 */

public class ExpenseEditDialogPresenter extends Presenter<ExpenseEditDialogScreen> {
    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    ExpensesInteractor expensesInteractor;

    @Override
    public void attachScreen(ExpenseEditDialogScreen screen) {
        super.attachScreen(screen);
        ExpenseRegistryApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        EventBus.getDefault().unregister(this);
    }

    public void SaveExpense(String place, Long amount, Date date, Long id){
        Expense expense = new Expense();
        expense.setPlace(place);
        expense.setAmount(amount);
        expense.setDate(date);
        if (id ==null) {
            networkExecutor.execute(() ->expensesInteractor.addExpense(expense));
        }else{
            expense.setId(id);
            networkExecutor.execute(() ->expensesInteractor.modifyExpense(id, expense));
        }
    }

    public void DeleteExpense(Long id){
        networkExecutor.execute(() ->expensesInteractor.deleteExpense(id));
    }

    @Subscribe
    public void ExpenseDeleted(DeleteExpenseEvent event){
        screen.ExpenseDeleted();
    }

    @Subscribe
    public void ExpenseSaved(ModifyExpenseEvent event){
        screen.ExpenseSaved();
    }
    @Subscribe
    public void ExpenseAdded(AddExpenseEvent event){
        screen.ExpenseSaved();
    }
}
