package com.ferenczcsabawallner.expenseregistry.mock;

import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;

import java.util.Date;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class MockExpenseApi implements ExpenseApi {
    @Override
    public Call<Expense> addExpense(Expense body) {
        return null;
    }

    @Override
    public Call<Expense> getExpenseById(Long expenseId) {
        return null;
    }

    @Override
    public Call<Void> updateExpense(Long expenseId, Expense expense) {
        return null;
    }

    @Override
    public Call<Void> deleteExpense(Long expenseId) {
        return null;
    }

    @Override
    public Call<List<Expense>> getExpense(Date timestamp) {
        return null;
    }
}
