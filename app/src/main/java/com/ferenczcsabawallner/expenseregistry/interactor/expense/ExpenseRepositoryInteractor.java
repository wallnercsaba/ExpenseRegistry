package com.ferenczcsabawallner.expenseregistry.interactor.expense;

import android.content.Context;

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

    @Inject
    Context context;

    public ExpenseRepositoryInteractor(){
        ExpenseRegistryApplication.injector.inject(this);
    }

    public void getExpensesByDate(Date date){
        expenseRepository.open(context);
        List<ExpenseRecord> expens = expenseRepository.getExpensesByDate(date);
        GetExpensesFromRepositoryByDateEvent event = new GetExpensesFromRepositoryByDateEvent(expens);
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }

    public void getExpenseById(Long id){
        expenseRepository.open(context);
        ExpenseRecord e = expenseRepository.getExpenseById(id);
        GetExpenseFromRepositoryById event = new GetExpenseFromRepositoryById(e);
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }

    public void saveExpense(Long id,
                            String place,
                            Date date,
                            Date timestamp,
                            Long amount){
        expenseRepository.open(context);
        expenseRepository.saveExpense(id, place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }

    public void updateExpense(Long id,
                              String place,
                              Date date,
                              Date timestamp,
                              Long amount){
        expenseRepository.open(context);
        expenseRepository.updateExpense(id, place, date, timestamp, amount);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }

    public void removeExpense(Long id){
        expenseRepository.open(context);
        expenseRepository.removeExpense(id);
        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }

    public Date getLastTimestamp(){
        expenseRepository.open(context);
        Date timeStamp = expenseRepository.getLastTimestamp();
        expenseRepository.close();
        return timeStamp;
    }

    public void processExpenses(List<Expense> expenses){
        expenseRepository.open(context);
        for (Expense e : expenses) {
            if (expenseRepository.isInDB(e.getId())){
                if (e.getDeleted()){
                    expenseRepository.removeExpense(e.getId());
                }else {
                    expenseRepository.updateExpense(e.getId(), e.getPlace(), e.getDate(), e.getTimestamp(), e.getAmount());
                }
            }else if(!e.getDeleted()){
                expenseRepository.saveExpense(e.getId(), e.getPlace(), e.getDate(),e.getTimestamp() , e.getAmount());
            }
        }

        ExpenseRepositoryUpdatedEvent event = new ExpenseRepositoryUpdatedEvent();
        EventBus.getDefault().post(event);
        expenseRepository.close();
    }
}
