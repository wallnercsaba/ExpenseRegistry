package com.ferenczcsabawallner.expenseregistry.repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public interface Repository {
    List<ExpenseRecord> getExpensesByDate(Date date);

    ExpenseRecord getExpenseById(Long id);

    void saveExpense(String place,
                     Date date,
                     Date timestamp,
                     Long amount);

    void updateExpense(Long id,
                       String place,
                       Date date,
                       Date timestamp,
                       Long amount);

    void removeExpense(Long id);

    Date getLastTimestamp();

    boolean isInDB(Long id);
}
