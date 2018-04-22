package com.ferenczcsabawallner.expenseregistry.interactor.expense.event;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class ExpenseRepositoryUpdatedEvent {
    private Throwable throwable;

    public ExpenseRepositoryUpdatedEvent(){}

    public ExpenseRepositoryUpdatedEvent(Throwable throwable){
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
