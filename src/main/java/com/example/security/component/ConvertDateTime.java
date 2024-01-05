package com.example.security.component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ConvertDateTime {

    public static LocalDateTime convertToLocalDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDateTime convertDateToLocalDateTime(String dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        return LocalDateTime.parse(dateTime, formatter);
    }

}
