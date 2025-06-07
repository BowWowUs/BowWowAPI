package com.pets.bowwow.global.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    @Getter
    @AllArgsConstructor
    public enum DateTimeFormat {

        YYYYMMDDHHMISS("yyyymmddhhmiss", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        ,YYYYMMDD("yyyymmdd", DateTimeFormatter.ofPattern("yyyyMMdd"))
        ,YYYY_MM_DD("yyyy-mm-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        ,HHMISS("hhmiss", DateTimeFormatter.ofPattern("HHmmss"))
        ,DEFAULT("default", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ;

        private final String code;
        private final DateTimeFormatter formatter;

        public static DateTimeFormat fromCode(String code) {
            for (DateTimeFormat format : values()) {
                if (format.code.equalsIgnoreCase(code))
                    return format;
            }
            throw new IllegalArgumentException("Invalid code: " + code);
        }

    }

    public static String getCurrentDate(String code) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormat format = DateTimeFormat.fromCode(code);

        return currentDateTime.format(format.getFormatter());
    }

    public static String getFutureDate(String code, int minutes) {
        LocalDateTime futureDateTime = LocalDateTime.now().plusMinutes(minutes);
        DateTimeFormat format = DateTimeFormat.fromCode(code);

        return futureDateTime.format(format.getFormatter());
    }

    public static String getPastDate(String code, int minutes) {
        LocalDateTime pastDateTime = LocalDateTime.now().minusMinutes(minutes);
        DateTimeFormat format = DateTimeFormat.fromCode(code);

        return pastDateTime.format(format.getFormatter());
    }

    
    public static LocalDate parseToLocalDate(String dateStr, DateTimeFormat code){
        return LocalDate.parse(dateStr, code.getFormatter());
    }

    public static LocalDate parseToLocalDate(String dateStr, String code){
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(code));
    }

    public static LocalDateTime parseToLocalDateTime(String dateStr, String code) {
        DateTimeFormat format = DateTimeFormat.fromCode(code);
        return LocalDateTime.parse(dateStr, format.getFormatter());
    }

    public static String formatLocalDateTime(LocalDateTime dateTime, String code) {
        DateTimeFormat format = DateTimeFormat.fromCode(code);
        return dateTime.format(format.getFormatter());
    }

    public static String formatLocalDate(LocalDate date, String code) {
        DateTimeFormat format = DateTimeFormat.fromCode(code);
        return date.format(format.getFormatter());
    }


}