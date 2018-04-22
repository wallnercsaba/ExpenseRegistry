package com.ferenczcsabawallner.expenseregistry.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ferenczcsabawallner.expenseregistry.R;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.List;

/**
 * Created by Csabi on 2018. 04. 22..
 */

public class ExpenseArrayAdapter extends BaseAdapter {
    List<ExpenseRecord> expens;

    Context context;

    public ExpenseArrayAdapter(Context context, List<ExpenseRecord> expens){
        this.context = context;
        this.expens = expens;
    }

    @Override
    public int getCount() {
        return expens.size();
    }

    @Override
    public Object getItem(int i) {
        return expens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return expens.get(i).getId();//TODO ??????????????????????????????????????????
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_expenseitem, viewGroup, false);

        TextView placeTextView = rowView.findViewById(R.id.place_textView);
        TextView amountTextView = rowView.findViewById(R.id.amount_textView);

        //set textview from expens list
        ExpenseRecord expenseRecord = expens.get(i);
        placeTextView.setText(expenseRecord.place);
        amountTextView.setText(expenseRecord.amount.toString());

        return rowView;
    }
}
