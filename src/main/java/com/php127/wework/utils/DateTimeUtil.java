package com.php127.wework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String date() {
        Date Now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String date = ft.format(Now);
        return date;
    }

    public static String time() {
        Date Now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        String time = ft.format(Now);
        return time;
    }

    public static String dateTime() {
        Date Now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = ft.format(Now);
        return datetime;
    }

    public static String format(String pattern) {
        Date Now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(pattern);
        String data = ft.format(Now);
        return data;
    }
}
