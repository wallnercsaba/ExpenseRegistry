package com.ferenczcsabawallner.expenseregistry.ui.editDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;
import com.ferenczcsabawallner.expenseregistry.interactor.expenses.ExpensesInteractor;
import com.ferenczcsabawallner.expenseregistry.model.Expense;
import com.ferenczcsabawallner.expenseregistry.repository.ExpenseRecord;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class ExpenseEditDialog extends Dialog {

    @Inject
    ExpensesInteractor expensesInteractor;

    EditText placeEditText;
    EditText amountEditText;

    public ExpenseEditDialog(@NonNull Context context, final Date date, final ExpenseRecord expenseRecord) {
        super(context);
        this.setContentView(R.layout.dialog_expenseedit);

        ExpenseRegistryApplication.injector.inject(this);

        placeEditText = findViewById(R.id.place_edittext);
        amountEditText = findViewById(R.id.amount_edittext);

        if (expenseRecord !=null){
            placeEditText.setText(expenseRecord.place);
            amountEditText.setText(expenseRecord.amount.toString());
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ExpenseRecord

                Expense expense = new Expense();
                expense.setPlace(placeEditText.getText().toString());
                expense.setAmount(Long.getLong(amountEditText.getText().toString()));
                expense.setDate(date);
                if (expenseRecord ==null) {
                    expensesInteractor.addExpense(expense);
                }else{
                    expense.setId(expenseRecord.getId());
                    expensesInteractor.modifyExpense(expenseRecord.getId(), expense);
                }
                cancel();
            }
        });
        Button deleteButton = findViewById(R.id.delete_button);
        if (expenseRecord==null){
            deleteButton.setActivated(false);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expensesInteractor.deleteExpense(expenseRecord.getId());

                    cancel();
                }
            });
        }

        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }


}
