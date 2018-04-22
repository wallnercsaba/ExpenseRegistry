package com.ferenczcsabawallner.expenseregistry.interactor.expense.event;

import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class GetExpenseFromRepositoryById {
    private ExpenseRecord expenseRecord;
    private Throwable throwable;

    public GetExpenseFromRepositoryById(){

    }

    public GetExpenseFromRepositoryById(ExpenseRecord expenseRecord){
        this.expenseRecord = expenseRecord;
    }

    public ExpenseRecord GetExpense(){return expenseRecord;}
    public void SetExpense(ExpenseRecord expenseRecord){this.expenseRecord = expenseRecord;}

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
