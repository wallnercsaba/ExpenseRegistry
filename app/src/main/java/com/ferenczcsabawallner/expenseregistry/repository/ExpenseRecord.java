package com.ferenczcsabawallner.expenseregistry.repository;

import com.orm.SugarRecord;

/**
 * Created by Csabi on 2018. 04. 17..
 */

public class ExpenseRecord extends SugarRecord {
    public String place;
    public Long date;
    public Long timestamp;
    public Long amount;

    public ExpenseRecord(){}
    public ExpenseRecord(Long id,
                         String place,
                         Long date,
                         Long timestamp,
                         Long amount){
        this.setId(id);
        this.amount = amount;
        this.place = place;
        this.date = date;
        this.timestamp = timestamp;
    }

//    public ExpenseRecord(String place,
//                         Long date,
//                         Long timestamp,
//                         Long amount){
//        this.place = place;
//        this.date = date;
//        this.timestamp = timestamp;
//        this.amount = amount;
//    }

}
