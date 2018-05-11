package com.ferenczcsabawallner.expenseregistry.util;

import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 05. 06..
 */

public class ExpenseHelper {
    public static Expense MakeExpense(long id, Date date, long amount, String place, boolean deleted, Date timestamp){
        Expense e = new Expense();
        e.setId(id);
        e.setDate(date);
        e.setAmount(amount);
        e.setPlace(place);
        e.setDeleted(deleted);
        e.setTimestamp(timestamp);

        return e;
    }

    public static ExpenseRecord MakeExpenseRecord(long id, Date date, long amount, String place, boolean deleted, Date timestamp){
        ExpenseRecord e = new ExpenseRecord(id,place,date.getTime(),timestamp.getTime(),amount);
        return e;
    }

    public static ExpenseRecord MakeExpenseRecord(Expense expense){
        ExpenseRecord e = new ExpenseRecord(expense.getId(),expense.getPlace(), expense.getDate().getTime(), expense.getTimestamp().getTime(), expense.getAmount());
        return e;
    }

    public static List<ExpenseRecord> ExpensesToExpenseRecords(List<Expense> expenses){
        List<ExpenseRecord> expenseRecords = new ArrayList<>();
        for (Expense e : expenses) {
            expenseRecords.add(MakeExpenseRecord(e));
        }
        return expenseRecords;
    }
}
