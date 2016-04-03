package org.aplie.android.myapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static String dayMonthYear(Calendar calendar){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(calendar.getTime());
    }

    public static String formatearDBDate(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return formateador.format(calendar.getTime());
    }

    public static String dayMonthYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return formateador.format(calendar.getTime());
    }
}
