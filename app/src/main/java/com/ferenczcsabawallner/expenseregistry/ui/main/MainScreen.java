package com.ferenczcsabawallner.expenseregistry.ui.main;

import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public interface MainScreen {
    void ShowExpenses(List<ExpenseRecord> expens);
    void ShowDialog(Date selectedDate, ExpenseRecord expenseRecord);
}
