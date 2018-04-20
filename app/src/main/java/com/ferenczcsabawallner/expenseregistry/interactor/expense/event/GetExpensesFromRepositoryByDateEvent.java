package com.ferenczcsabawallner.expenseregistry.interactor.expense.event;

import com.ferenczcsabawallner.expenseregistry.repository.Expense;

import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class GetExpensesFromRepositoryByDateEvent {
    private List<Expense> expenses;
    private Throwable throwable;

    public GetExpensesFromRepositoryByDateEvent(){

    }

    public GetExpensesFromRepositoryByDateEvent(List<Expense> expenses){
        this.expenses=expenses;
    }

    public List<Expense> GetExpenses(){return expenses;}
    public void SetExpenses(List<Expense> expenses){this.expenses=expenses;}

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
