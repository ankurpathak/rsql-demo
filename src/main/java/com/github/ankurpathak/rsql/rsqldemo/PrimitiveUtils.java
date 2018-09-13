package com.github.ankurpathak.rsql.rsqldemo;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PrimitiveUtils {

    public  static BigInteger toBigInteger(String value){
        return BigInteger.valueOf(toLong(value));
    }


    public static long toLong(String value){
        try{
            return Long.valueOf(value);
        }catch (NumberFormatException ex){
            return 0L;
        }
    }


    public static int toInteger(String value){
        try{
            return Integer.valueOf(value);
        }catch (NumberFormatException ex){
            return 0;
        }
    }

    public static Date toDate(String value){
        try{
            SimpleDateFormat iso = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            return iso.parse(value);
        }catch (ParseException ex){
            return Calendar.getInstance().getTime();
        }
    }


    public static LocalDateTime toLocalDateTime(String value){
        try{
            return LocalDateTime.parse(value);
        }catch (DateTimeParseException ex){
            return LocalDateTime.now();
        }
    }
}
