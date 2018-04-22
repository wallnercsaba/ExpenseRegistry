package com.ferenczcsabawallner.expenseregistry.interactor.expenses.event;

import com.ferenczcsabawallner.expenseregistry.model.Expense;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class AddExpenseEvent {
    private int code;
    private Throwable throwable;
    private Expense expense;

    public AddExpenseEvent(){}

    public AddExpenseEvent(int code, Expense expense, Throwable throwable){
        this.code = code;
        this.expense=expense;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Expense getExpense(){return this.expense;}

    public void setExpense(Expense expense){this.expense=expense;}
}
