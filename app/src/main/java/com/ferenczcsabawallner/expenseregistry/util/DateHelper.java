package com.ferenczcsabawallner.expenseregistry.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Csabi on 2018. 04. 22..
 */

public class DateHelper {
    public static Date JustDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        return c.getTime();
    }
}
