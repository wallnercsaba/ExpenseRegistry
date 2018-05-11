package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;
import com.ferenczcsabawallner.expenseregistry.ui.editDialog.ExpenseEditDialog;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 12..
 */

public class MainFragment extends Fragment implements MainScreen, AdapterView.OnItemClickListener, OnDayClickListener {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Context context;

    ListView expenseListView;
    CalendarView calendarView;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

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
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ExpenseRegistryApplication.injector.inject(this);

        calendarView= v.findViewById(R.id.calendar);
        //calendarView.setOnDateChangeListener(this);
        calendarView.setOnDayClickListener(this);

        expenseListView = v.findViewById(R.id.expensees_listview);

        expenseListView.setOnItemClickListener(this);

        FloatingActionButton floatButtonAdd = v.findViewById(R.id.addexpense_button);
        floatButtonAdd.setOnClickListener(view -> mainPresenter.ShowDialog(null));
        FloatingActionButton floatButtonSync = v.findViewById(R.id.syncwithserver_button);
        floatButtonSync.setOnClickListener(view -> mainPresenter.SyncWithServer());

        return v;
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }*/

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void ShowExpenses(List<ExpenseRecord> expens) {
        ExpenseArrayAdapter adapter = new ExpenseArrayAdapter(context, expens);
        expenseListView.setAdapter(adapter);
    }

    @Override
    public void ShowDialog(Date selectedDate, ExpenseRecord expenseRecord) {
        ExpenseEditDialog dialog = new ExpenseEditDialog(getActivity(),selectedDate,expenseRecord);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ExpenseArrayAdapter adapter = (ExpenseArrayAdapter)adapterView.getAdapter();
        mainPresenter.ShowDialog((ExpenseRecord)adapter.getItem(i));
    }

   /** @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                    int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mainPresenter.SelectDay(cal.getTime());
    }*/

    @Override
    public void onDayClick(EventDay eventDay) {
        /*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, eventDay.getCalendar().get());
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);*/
        mainPresenter.SelectDay(eventDay.getCalendar().getTime());
    }

}