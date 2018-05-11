package com.ferenczcsabawallner.expenseregistry.mock;

import android.content.Context;

import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.repository.Repository;
import com.ferenczcsabawallner.expenseregistry.util.DateHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Csabi on 2018. 04. 24..
 */

public class MockRepository implements Repository {
    private List<ExpenseRecord> expenses;
    public static Map<Long,ExpenseRecord> expensesById;
    public static Map<Long, List<ExpenseRecord>> expensesByDate;
//    public static long nextId;

    public MockRepository(){
        expenses = new ArrayList<>();
        expensesById = new HashMap<>();
        expensesByDate = new HashMap<>();
    }

    @Override
    public void open(Context context) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<ExpenseRecord> getExpensesByDate(Date date) {
        Long l = date.getTime();
        return expensesByDate.get(date.getTime());
    }

    @Override
    public ExpenseRecord getExpenseById(Long id) {
        return expensesById.get(id);
    }

    @Override
    public void saveExpense(Long id, String place, Date date, Date timestamp, Long amount) {
        ExpenseRecord e = new ExpenseRecord(id, place,DateHelper.JustDate(date).getTime(), timestamp.getTime(), amount);
        expensesById.put(id,e);
        List<ExpenseRecord> eList = expensesByDate.get(e.date);
        if (eList==null) expensesByDate.put(e.date, new ArrayList<>());
        expensesByDate.get(e.date).add(e);
        expenses.add(e);
    }

    @Override
    public void updateExpense(Long id, String place, Date date, Date timestamp, Long amount) {
        ExpenseRecord e = expensesById.get(id);
        e.place=place;
        e.date=DateHelper.JustDate(date).getTime();
        e.timestamp=timestamp.getTime();
        e.amount=amount;
    }

    @Override
    public void removeExpense(Long id) {
        ExpenseRecord e = expensesById.get(id);
        expensesById.remove(id);
        expensesByDate.get(e.date).remove(e);
        expenses.remove(e);
    }

    @Override
    public Date getLastTimestamp() {
        if (expenses.size()==0) return new Date(0);
         expenses.sort((o1, o2) -> {
             if (o1.timestamp==o2.timestamp) return 0;
             return o1.timestamp>o2.timestamp?1:-1;
         });
        return new Date(expenses.get(0).timestamp);
    }

    @Override
    public boolean isInDB(Long id) {
        return expensesById.get(id) != null;
    }
}
