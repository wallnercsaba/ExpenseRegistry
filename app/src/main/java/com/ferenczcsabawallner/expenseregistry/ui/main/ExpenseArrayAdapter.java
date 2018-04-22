package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ferenczcsabawallner.expenseregistry.R;

/**
 * Created by Csabi on 2018. 04. 22..
 */

public class ExpenseArrayAdapter extends BaseAdapter {
    //List<Expense> expenses;

    Context context;

    public ExpenseArrayAdapter(Context context/*, List<Expenses> expenses*/){
        this.context = context;
        //this.expenses=expenses;
    }

    @Override
    public int getCount() {
        return 0; //return Expenses.count???
    }

    @Override
    public Object getItem(int i) {
        return null;//return Expenses[i]???
    }

    @Override
    public long getItemId(int i) {
        return 0;//return Expenses[i].id???
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_expenseitem, viewGroup, false);

        TextView placeTextView = rowView.findViewById(R.id.place_textView);
        TextView amountTextView = rowView.findViewById(R.id.amount_textView);

        //set textview from expenses list

        return rowView;
    }
}
