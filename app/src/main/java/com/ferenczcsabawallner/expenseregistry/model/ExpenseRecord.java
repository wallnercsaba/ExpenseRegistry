package com.ferenczcsabawallner.expenseregistry.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class ExpenseRecord extends SugarRecord<ExpenseRecord> {
    Long id;
    String place;
    Date date;
    Date timestamp;

    public ExpenseRecord(){}
    public ExpenseRecord(Long id,
            String place,
            Date date,
            Date timestamp){
        this.id = id;
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
    }

}
