package com.ferenczcsabawallner.expenseregistry.interactor.expenses.event;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class ModifyExpenseEvent {
    private int code;
    private Throwable throwable;
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
}
