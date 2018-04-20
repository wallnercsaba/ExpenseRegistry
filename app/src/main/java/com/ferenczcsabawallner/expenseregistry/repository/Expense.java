package com.ferenczcsabawallner.expenseregistry.repository;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class Expense extends SugarRecord<Expense> {
    String place;
    String date;
    String timestamp;

    public Expense(){}
    public Expense(Long id,
                   String place,
                   String date,
                   String timestamp){
        this.setId(id);
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
    }

    public Expense(String place,
                   String date,
                   String timestamp){
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
    }

}
