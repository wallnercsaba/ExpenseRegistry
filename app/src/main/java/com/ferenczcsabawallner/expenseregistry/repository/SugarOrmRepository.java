package com.ferenczcsabawallner.expenseregistry.repository;

import android.widget.CalendarView;

import com.ferenczcsabawallner.expenseregistry.util.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class SugarOrmRepository implements Repository {

    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<ExpenseRecord> getExpensesByDate(Date date) {
        return ExpenseRecord.find(ExpenseRecord.class, "date = ?", fmt.format(date));
    }

    @Override
    public ExpenseRecord getExpenseById(Long id) {
        return ExpenseRecord.findById(ExpenseRecord.class,id);
    }

    @Override
    public void saveExpense(String place,
                            Date date,
                            Date timestamp,
                            Long amount) {

        ExpenseRecord e = new ExpenseRecord(place, DateHelper.JustDate(date).getTime(), timestamp.getTime(), amount);
        e.save();
    }

    @Override
    public void updateExpense(Long id,
                              String place,
                              Date date,
                              Date timestamp,
                              Long amount) {
        ExpenseRecord e = ExpenseRecord.findById(ExpenseRecord.class, id);
        e.place=place;
        e.date=DateHelper.JustDate(date).getTime();
        e.timestamp=timestamp.getTime();
        e.amount=amount;
        e.save();
    }

    @Override
    public void removeExpense(Long id) {
        ExpenseRecord e = ExpenseRecord.findById(ExpenseRecord.class, id);
        e.delete();
    }

    @Override
    public Date getLastTimestamp() {
        ExpenseRecord e =  ExpenseRecord.find(ExpenseRecord.class, null, null, null, "timestamp DESC", "1").get(0);
        return new Date(e.timestamp);
    }

    @Override
    public boolean isInDB(Long id) {
        return ExpenseRecord.findById(ExpenseRecord.class, id)!=null;
    }
}
