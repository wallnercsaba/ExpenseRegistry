package com.ferenczcsabawallner.expenseregistry.ui.main;

import com.ferenczcsabawallner.expenseregistry.ui.Presenter;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by Csabi on 2018. 04. 15..
 */

public class MainPresenter extends Presenter<MainScreen> {

    public void SelectDay(Date date){

        //TODO Get Expenses from repository
        screen.ShowExpenses();
    }
}
