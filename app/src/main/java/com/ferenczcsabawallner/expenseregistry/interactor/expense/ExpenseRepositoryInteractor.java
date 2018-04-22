package com.ferenczcsabawallner.expenseregistry.interactor.expense;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.ExpenseRepositoryUpdatedEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.GetExpenseFromRepositoryById;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.GetExpensesFromRepositoryByDateEvent;
import com.ferenczcsabawallner.expenseregistry.repository.Expense;
import com.ferenczcsabawallner.expenseregistry.repository.Repository;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class ExpenseRepositoryInteractor {
    @Inject
    Repository expenseRepository;

    public ExpenseRepositoryInteractor(){
        ExpenseRegistryApplication.injector.inject(this);
    }

    void getExpensesByDate(Date date){
        List<Expense> expenses = expenseRepository.getExpensesByDate(date);
        GetExpensesFromRepositoryByDateEvent event = new GetExpensesFromRepositoryByDateEvent(expenses);
        EventBus.getDefault().post(event);
    }

    void getExpenseById(Long id){
        Expense e = expenseRepository.getExpenseById(id);
        GetExpenseFromRepositoryById event = new GetExpenseFromRepositoryById(e);
        EventBus.getDefault().post(event);
    }

    void saveExpense(String place,
                     String date,
                     String timestamp,
                     Long amount){
        expenseRepository.saveExpense(place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

    void updateExpense(Long id,
                       String place,
                       String date,
                       String timestamp,
                       Long amount){
        expenseRepository.updateExpense(id, place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

    void removeExpense(Long id){
        expenseRepository.removeExpense(id);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

}
