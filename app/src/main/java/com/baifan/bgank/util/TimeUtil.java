package com.baifan.bgank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by baifan on 16/9/2.
 */
public class TimeUtil {
    public static String[] getTimeArrayFromDate(Date date){
        String[] timeArray = new String[3];
        SimpleDateFormat dfYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat dfMonth = new SimpleDateFormat("MM");
        SimpleDateFormat dfDate = new SimpleDateFormat("dd");
        timeArray[0] = dfYear.format(date);
        timeArray[1] = dfMonth.format(date);
        timeArray[2] = dfDate.format(date);
        return timeArray;
    }
}
