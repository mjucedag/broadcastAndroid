package com.example.mj_uc.broadcast;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {

    public static String getCurrentTime(){

        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentDate(){

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(Calendar.getInstance().getTime());
    }
}
