package com.ferenczcsabawallner.expenseregistry.interactor.expense.event;

import com.ferenczcsabawallner.expenseregistry.repository.Expense;

import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class GetExpenseFromRepositoryById {
    private Expense expense;
    private Throwable throwable;

    public GetExpenseFromRepositoryById(){

    }

    public GetExpenseFromRepositoryById(Expense expense){
        this.expense=expense;
    }

    public Expense GetExpense(){return expense;}
    public void SetExpense(Expense expense){this.expense=expense;}

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
