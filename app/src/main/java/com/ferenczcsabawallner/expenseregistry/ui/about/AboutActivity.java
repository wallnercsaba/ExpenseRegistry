package com.ferenczcsabawallner.expenseregistry.ui.about;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ferenczcsabawallner.expenseregistry.ExpenseRegistryApplication;
import com.ferenczcsabawallner.expenseregistry.R;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class AboutActivity extends AppCompatActivity implements AboutScreen {
    @Inject
    AboutPresenter aboutPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        ExpenseRegistryApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        aboutPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        aboutPresenter.detachScreen();
    }
}
