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
    Long amount;

    public Expense(){}
    public Expense(Long id,
                   String place,
                   String date,
                   String timestamp,
                   Long amount){
        this.setId(id);
        this.amount = amount;
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
    }

    public Expense(String place,
                   String date,
                   String timestamp,
                   Long amount){
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
        this.amount = amount;
    }

}
