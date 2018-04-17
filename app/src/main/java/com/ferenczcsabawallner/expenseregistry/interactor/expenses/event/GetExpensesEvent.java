package com.ferenczcsabawallner.expenseregistry.interactor.expenses.event;

import com.ferenczcsabawallner.expenseregistry.model.Expense;

import java.util.List;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class GetExpensesEvent {
    private int code;
    private Throwable throwable;
    private List<Expense> expenses;

    public GetExpensesEvent(){

    }

    public GetExpensesEvent(int code, List<Expense> expenses, Throwable throwable){
        this.code = code;
        this.expenses=expenses;
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

    public List<Expense> GetExpenses(){return expenses;}
    public void SetExpenses(List<Expense> expenses){this.expenses=expenses;}

}
