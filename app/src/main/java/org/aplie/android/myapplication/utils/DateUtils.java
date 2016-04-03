package org.aplie.android.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;

import org.aplie.android.myapplication.R;

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

    public static String month(Context context, String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        int month = calendar.get(Calendar.MONTH);
        Resources res = context.getResources();
        String[] months = res.getStringArray(R.array.month);
        return months[month];
    }

    public static String formatNumberLessTen(int num){
        if(num<10){
            return "0"+num;
        }else{
            return String.valueOf(num);
        }
    }
}
