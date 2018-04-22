package com.ferenczcsabawallner.expenseregistry.interactor.expense;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.ExpenseRepositoryUpdatedEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.GetExpenseFromRepositoryById;
import com.ferenczcsabawallner.expenseregistry.interactor.expense.event.GetExpensesFromRepositoryByDateEvent;
import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
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

    public void getExpensesByDate(Date date){
        List<ExpenseRecord> expens = expenseRepository.getExpensesByDate(date);
        GetExpensesFromRepositoryByDateEvent event = new GetExpensesFromRepositoryByDateEvent(expens);
        EventBus.getDefault().post(event);
    }

    public void getExpenseById(Long id){
        ExpenseRecord e = expenseRepository.getExpenseById(id);
        GetExpenseFromRepositoryById event = new GetExpenseFromRepositoryById(e);
        EventBus.getDefault().post(event);
    }

    public void saveExpense(String place,
                            Date date,
                            Date timestamp,
                            Long amount){
        expenseRepository.saveExpense(place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

    public void updateExpense(Long id,
                              String place,
                              Date date,
                              Date timestamp,
                              Long amount){
        expenseRepository.updateExpense(id, place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

    public void removeExpense(Long id){
        expenseRepository.removeExpense(id);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
    }

    public Date getLastTimestamp(){
        return expenseRepository.getLastTimestamp();
    }

    public void processExpenses(List<Expense> expenses){
        for (Expense e : expenses) {
            if (expenseRepository.isInDB(e.getId())){
                expenseRepository.updateExpense(e.getId(), e.getPlace(), e.getDate(), e.getTimestamp(), e.getAmount());
            }else{
                expenseRepository.saveExpense(e.getPlace(), e.getDate(),e.getTimestamp() , e.getAmount());
            }
            ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
            EventBus.getDefault().post(event);
        }
    }
}
