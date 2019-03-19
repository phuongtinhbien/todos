package com.example.ui.todos.ultil;

import java.util.Calendar;

public class Date {
    public static long getTime (){
        return Calendar.getInstance().getTime().getTime();
    }

    public static long getTime(int day, int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime().getTime();
    }
}
