package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.content.Context;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.ExpenseRepositoryInteractor;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.ExpenseRepositoryUpdatedEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.GetExpensesFromRepositoryByDateEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.ExpensesInteractor;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.AddExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.DeleteExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.GetExpensesEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.ModifyExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.ui.Presenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    ExpenseRepositoryInteractor expenseRepositoryInteractor;

    @Inject
    ExpensesInteractor expensesInteractor;

    @Inject
    Context context;

    Date selectedDate;

    public MainPresenter(){
        ExpenseRegistryApplication.injector.inject(this);
    }

    public void SelectDay(Date date){
        selectedDate = date;
        expenseRepositoryInteractor.getExpensesByDate(date);
    }

    public void ShowDialog(ExpenseRecord expenseRecord){
        screen.ShowDialog(selectedDate, expenseRecord);
    }

    public void SyncWithServer(){
        expensesInteractor.getExpenses(expenseRepositoryInteractor.getLastTimestamp());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetExpensesFromRepositoryByDateEvent(GetExpensesFromRepositoryByDateEvent event){
        screen.ShowExpenses(event.GetExpenses());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddExpenseEvent(AddExpenseEvent event){
        Expense e = event.getExpense();
        expenseRepositoryInteractor.saveExpense(e.getPlace(), e.getDate(), e.getTimestamp(), e.getAmount());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteExpenseEvent(DeleteExpenseEvent event){
        Long id = event.getId();
        expenseRepositoryInteractor.removeExpense(id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetExpensesEvent(GetExpensesEvent event){
        expenseRepositoryInteractor.processExpenses(event.GetExpenses());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onModifyExpenseEvent(ModifyExpenseEvent event){
        Expense e = event.getExpense();
        expenseRepositoryInteractor.updateExpense(e.getId(),e.getPlace(),e.getDate(),e.getTimestamp(),e.getAmount());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onExpenseRepositoryUpdatedEvent(ExpenseRepositoryUpdatedEvent event){
        SelectDay(selectedDate);
    }
}
