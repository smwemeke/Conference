package com.pluralsight.conference.model;

import java.time.Month;

public class MonthDemo {
    public static void main(String [] args){
        Month month = Month.APRIL;
        var result = switch(month){
            case JANUARY, MARCH, MAY, JULY, SEPTEMBER,NOVEMBER -> "odd";
            case FEBRUARY, APRIL, JUNE, AUGUST, OCTOBER, DECEMBER -> "even";
            default -> "neither";
        };
        System.out.println("Month: " + result);
    }
}
