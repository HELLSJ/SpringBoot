package com.example.springblog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String dateFormat(Date date){
        //2026-03-07 11:24
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(dateFormat(new Date()));
    }
}
