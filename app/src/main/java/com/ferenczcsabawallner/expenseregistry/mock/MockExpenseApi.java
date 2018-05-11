package com.ferenczcsabawallner.expenseregistry.mock;

import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.network.expense.ExpenseApi;
import com.ferenczcsabawallner.expenseregistry.util.ExpenseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Csabi on 2018. 04. 20..
 */

public class MockExpenseApi implements ExpenseApi {
    private List<Expense> expenses;
    public static Map<Date, List<Expense>> expensesByDate;
    public static Map<Long, Expense> expensesById;
    public static long nextId;
    public MockExpenseApi(){
        expensesByDate = new HashMap<>();
        expenses = new ArrayList<>();
        expensesById = new HashMap<>();
        List<Expense> expenseList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND,0);
        cal.set(Calendar.AM_PM, Calendar.AM);


        expenseList.add(ExpenseHelper.MakeExpense(4,cal.getTime(), 202300, "spar", false, cal.getTime()));
        expenseList.add(ExpenseHelper.MakeExpense(1,cal.getTime(), 231000, "spar", false, cal.getTime()));
        expenseList.add(ExpenseHelper.MakeExpense(2,cal.getTime(), 2000, "spar", false, cal.getTime()));
        expenseList.add(ExpenseHelper.MakeExpense(3,cal.getTime(), 2045100, "spar", false, cal.getTime()));
        nextId=5;
        expenses.addAll(expenseList);
        expensesByDate.put(cal.getTime(), expenseList);

        for (Expense e: expenseList
             ) {
            expensesById.put(e.getId(), e);
        }

    }

    @Override
    public Call<Expense> addExpense(final Expense body) {
        body.setId(nextId);
        expenses.add(body);
        expensesById.put(nextId, body);
        body.setTimestamp(Calendar.getInstance().getTime());
        List<Expense> eList = expensesByDate.get(body.getDate());
        if (eList==null) expensesByDate.put(body.getDate(), new ArrayList<>());
        expensesByDate.get(body.getDate()).add(body);
        nextId++;
        Call<Expense> call = new Call<Expense>() {
            @Override
            public Response<Expense> execute() throws IOException {
                return Response.success(body);
            }

            @Override
            public void enqueue(Callback<Expense> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Expense> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<Expense> getExpenseById(final Long expenseId) {
        Call<Expense> call = new Call<Expense>() {
            @Override
            public Response<Expense> execute() throws IOException {
                return Response.success(expenses.get(expenseId.intValue()));
            }

            @Override
            public void enqueue(Callback<Expense> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Expense> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<Void> updateExpense(final Long expenseId, Expense expense) {
        Call<Void> call = new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                Expense e = expensesById.get(expenseId);
                if (e!=null){
                    e.setTimestamp(Calendar.getInstance().getTime());
                    e.setPlace(expense.getPlace());
                    e.setAmount(expense.getAmount());

                    return Response.success(null);
                }else{
                    return Response.error(404,null);
                }
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<Void> deleteExpense(Long expenseId) {
        Call<Void> call = new Call<Void>() {
            @Override
            public Response<Void> execute() throws IOException {
                Expense e = expensesById.get(expenseId);
                if (e!=null){
                    e.setTimestamp(Calendar.getInstance().getTime());
                    e.setDeleted(true);

                    return Response.success(null);
                }else{
                    return Response.error(404,null);
                }
            }

            @Override
            public void enqueue(Callback<Void> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Void> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }

    @Override
    public Call<List<Expense>> getExpense(Date timestamp) {
        Call<List<Expense>> call = new Call<List<Expense>>() {
            @Override
            public Response<List<Expense>> execute() throws IOException {
                if (timestamp==null){
                    return Response.success(expenses);
                }

//                List<Expense> filteredList = new ArrayList<>();
//                for (Expense e: expenses
//                     ) {
//                    if (e.getTimestamp())
//                }
                Stream<Expense> e = expenses.stream().filter(o-> o.getTimestamp().after(timestamp));

                return Response.success(new ArrayList<Expense>(e.collect(Collectors.toList())));

            }

            @Override
            public void enqueue(Callback<List<Expense>> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<List<Expense>> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        return call;
    }
}
