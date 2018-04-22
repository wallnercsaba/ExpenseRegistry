package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 12..
 */

public class MainActivity extends AppCompatActivity implements MainScreen, AdapterView.OnItemClickListener, CalendarView.OnDateChangeListener {

    @Inject
    MainPresenter mainPresenter;

    ListView expenseListView;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpenseRegistryApplication.injector.inject(this);

        calendarView= findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(this);

        expenseListView = findViewById(R.id.expensees_listview);

        expenseListView.setOnItemClickListener(this);

        FloatingActionButton floatButtonAdd = findViewById(R.id.addexpense_button);
        floatButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.ShowDialog(null);
            }
        });
        FloatingActionButton floatButtonSync = findViewById(R.id.syncwithserver_button);
        floatButtonSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.SyncWithServer();
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
    public void ShowExpenses(List<ExpenseRecord> expens) {
        ExpenseArrayAdapter adapter = new ExpenseArrayAdapter(this, expens);
        expenseListView.setAdapter(adapter);
    }

    @Override
    public void ShowDialog(Date selectedDate, ExpenseRecord expenseRecord) {
        ExpenseEditDialog dialog = new ExpenseEditDialog(this,selectedDate,expenseRecord);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ExpenseArrayAdapter adapter = (ExpenseArrayAdapter)adapterView.getAdapter();
        mainPresenter.ShowDialog((ExpenseRecord)adapter.getItem(i));
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                    int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mainPresenter.SelectDay(cal.getTime());
    }
}