package com.ferenczcsabawallner.expenseregistry.repository;

import android.content.Context;

import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public interface Repository {
    List<Expense> getExpensesByDate(Date date);

    Expense getExpenseById(Long id);

    void saveExpense(String place,
                     String date,
                     String timestamp,
                     Long amount);

    void updateExpense(Long id,
                       String place,
                       String date,
                       String timestamp,
                       Long amount);

    void removeExpense(Long id);
}
