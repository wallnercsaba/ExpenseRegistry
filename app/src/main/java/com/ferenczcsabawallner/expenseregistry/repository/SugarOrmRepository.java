package com.ferenczcsabawallner.expenseregistry.repository;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class SugarOrmRepository implements Repository {

    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Expense> getExpensesByDate(Date date) {
        return Expense.find(Expense.class, "date = ?", fmt.format(date));
    }

    @Override
    public Expense getExpenseById(Long id) {
        return Expense.findById(Expense.class,id);
    }

    @Override
    public void saveExpense(String place,
                            String date,
                            String timestamp,
                            Long amount) {
        Expense e = new Expense(place, date, timestamp, amount);
        e.save();
    }

    @Override
    public void updateExpense(Long id,
                              String place,
                              String date,
                              String timestamp,
                              Long amount) {
        Expense e = Expense.findById(Expense.class, id);
        e.place=place;
        e.date=date;
        e.timestamp=timestamp;
        e.amount=amount;
        e.save();
    }

    @Override
    public void removeExpense(Long id) {
        Expense e = Expense.findById(Expense.class, id);
        e.delete();
    }
}
