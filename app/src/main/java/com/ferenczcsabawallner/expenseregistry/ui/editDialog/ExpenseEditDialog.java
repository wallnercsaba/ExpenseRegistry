package com.ferenczcsabawallner.expenseregistry.ui.editDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class ExpenseEditDialog extends Dialog implements ExpenseEditDialogScreen
{

    @Inject
    ExpenseEditDialogPresenter expenseEditDialogPresenter;


    EditText placeEditText;
    EditText amountEditText;

    Date date;
    ExpenseRecord  expenseRecord;

    public ExpenseEditDialog(@NonNull Context context, final Date date, final ExpenseRecord expenseRecord) {
        super(context);
        this.setContentView(R.layout.dialog_expenseedit);
        this.expenseRecord = expenseRecord;
        this.date=date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExpenseRegistryApplication.injector.inject(this);

        placeEditText = findViewById(R.id.place_edittext);
        amountEditText = findViewById(R.id.amount_edittext);

        if (expenseRecord !=null){
            placeEditText.setText(expenseRecord.place);
            amountEditText.setText(expenseRecord.amount.toString());
        }
        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(view -> expenseEditDialogPresenter.SaveExpense(placeEditText.getText().toString(), Long.parseLong(amountEditText.getText().toString()), date, expenseRecord==null?null:expenseRecord.getId()));

        Button deleteButton = findViewById(R.id.delete_button);
        if (expenseRecord!=null){
            deleteButton.setActivated(true);
            deleteButton.setOnClickListener(view -> expenseEditDialogPresenter.DeleteExpense(expenseRecord.getId()));
        }else{
            deleteButton.setActivated(false);
        }

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(view -> cancel());
    }

    @Override
    protected void onStart() {
        super.onStart();
        expenseEditDialogPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        expenseEditDialogPresenter.detachScreen();
    }

    @Override
    public void ExpenseSaved() {
        cancel();
    }

    @Override
    public void ExpenseDeleted() {
        cancel();
    }
}
