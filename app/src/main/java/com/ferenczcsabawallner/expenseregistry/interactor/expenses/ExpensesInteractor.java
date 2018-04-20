package com.ferenczcsabawallner.expenseregistry.interactor.expenses;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.AddExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.DeleteExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.GetExpensesEvent;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.event.ModifyExpenseEvent;
import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class ExpensesInteractor {

    @Inject
    ExpenseApi expenseApi;

    public ExpensesInteractor(){
        ExpenseRegistryApplication.injector.inject(this);
    }

    public void getExpenses(Date date){
        Call<List<Expense>> expenseQueryCall = expenseApi.getExpense(date);
        GetExpensesEvent event=new GetExpensesEvent();
        try {
            Response<List<Expense>> response = expenseQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.SetExpenses(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
    public void addExpense(Expense expense){
        Call<Expense> expenseQueryCall = expenseApi.addExpense(expense);
        AddExpenseEvent event = new AddExpenseEvent();
        try {
            Response<Expense> response = expenseQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setExpense(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
    public void modifyExpense(Long expenseId, Expense expense){
        Call<Void> expenseQueryCall = expenseApi.updateExpense(expenseId, expense);
        ModifyExpenseEvent event = new ModifyExpenseEvent();
        try {
            Response<Void> response = expenseQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setExpense(expense);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
    public void deleteExpense(Long expenseId){
        Call<Void> expenseQueryCall = expenseApi.deleteExpense(expenseId);
        DeleteExpenseEvent event = new DeleteExpenseEvent();
        try {
            Response<Void> response = expenseQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setId(expenseId);
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
