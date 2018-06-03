package com.digitalhomeland.storeadmin;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Asus on 3/19/2018.
 */

public class Utils {

    public static String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = df2.format(c.getTime());
        return  currentDate;
    }

    public static String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String currentTime = df.format(c.getTime());
        return  currentTime;
    }
}
