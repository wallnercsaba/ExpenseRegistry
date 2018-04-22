package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;

import java.util.Calendar;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 12..
 */

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    ListView expenseListView;

    //TODO Expense selectedExpense; or implement the dialog interface for the event handling

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpenseRegistryApplication.injector.inject(this);

        CalendarView calendarView= findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                mainPresenter.SelectDay(cal.getTime());
            }
        });

        expenseListView = findViewById(R.id.expensees_listview);

        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO Set selectedExpense
                //TODO popup editExpense and handle the actions
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void ShowExpenses() {
        ExpenseArrayAdapter adapter = new ExpenseArrayAdapter(this/*, new List<Expense>()*/);
        expenseListView.setAdapter(adapter);
    }
}