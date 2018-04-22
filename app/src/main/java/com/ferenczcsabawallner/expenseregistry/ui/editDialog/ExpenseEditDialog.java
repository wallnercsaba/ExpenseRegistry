package com.ferenczcsabawallner.expenseregistry.ui.editDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ferenczcsabawallner.expenseregistry.R;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class ExpenseEditDialog extends Dialog {

    //Expense expense;
    EditText placeEditText;
    EditText amountEditText;

    public ExpenseEditDialog(@NonNull Context context/*, Expense expense*/) {
        super(context);
        this.setContentView(R.layout.dialog_expenseedit);

        placeEditText = findViewById(R.id.place_edittext);
        amountEditText = findViewById(R.id.amount_edittext);

        //Set text if not new

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Expense
                placeEditText.getText().toString();
                Integer.getInteger(amountEditText.getText().toString());

                //save

                cancel();
            }
        });
        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete

                cancel();
            }
        });
        Button cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }


}
