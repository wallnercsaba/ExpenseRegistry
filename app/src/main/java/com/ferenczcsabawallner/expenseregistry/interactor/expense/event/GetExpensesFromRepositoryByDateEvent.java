package com.ferenczcsabawallner.expenseregistry.interactor.expense.event;

import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class GetExpensesFromRepositoryByDateEvent {
    private List<ExpenseRecord> expens;
    private Throwable throwable;

    public GetExpensesFromRepositoryByDateEvent(){

    }

    public GetExpensesFromRepositoryByDateEvent(List<ExpenseRecord> expens){
        this.expens = expens;
    }

    public List<ExpenseRecord> GetExpenses(){return expens;}
    public void SetExpenses(List<ExpenseRecord> expens){this.expens = expens;}

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
