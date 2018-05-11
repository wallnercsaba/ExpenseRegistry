package com.ferenczcsabawallner.expenseregistry.repository;

import android.content.Context;
import android.widget.CalendarView;

import com.ferenczcsabawallner.expenseregistry.util.DateHelper;
import com.orm.SugarContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.orm.SugarRecord.find;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<ExpenseRecord> getExpensesByDate(Date date) {
        return find(ExpenseRecord.class, "date = ?", Long.toString(date.getTime()));
    }

    @Override
    public ExpenseRecord getExpenseById(Long id) {
        return ExpenseRecord.findById(ExpenseRecord.class,id);
    }

    @Override
    public void saveExpense(Long id,
                            String place,
                            Date date,
                            Date timestamp,
                            Long amount) {

        ExpenseRecord e = new ExpenseRecord(id,place, DateHelper.JustDate(date).getTime(), timestamp.getTime(), amount);
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
        List<ExpenseRecord> list = ExpenseRecord.find(ExpenseRecord.class, null, null, null, "timestamp DESC", "1");
        if (list.size()==0){
            return new Date(0);
        }
        ExpenseRecord e = list.get(0);
        return new Date(e.timestamp);
    }

    @Override
    public boolean isInDB(Long id) {
        return ExpenseRecord.findById(ExpenseRecord.class, id)!=null;
    }
}
