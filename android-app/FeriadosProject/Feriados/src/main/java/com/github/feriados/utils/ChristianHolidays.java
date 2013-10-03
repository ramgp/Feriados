package com.github.feriados.utils;

import org.joda.time.DateTime;

/**
 * Created by ramgp on 10/2/13.
 * Based on answer from StackOverflow.com by Paul Sasik
 * URL: http://stackoverflow.com/questions/2510383/how-can-i-calculate-what-date-good-friday-falls-on-given-a-year
 */
public class ChristianHolidays {

  public static DateTime goodFriday(int year)
  {
    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int i = c / 4;
    int k = c % 4;
    int g = (8 * b + 13) / 25;
    int h = ((19 * a) + b - d - g + 15) % 30;
    int l = ((2 * e) + (2 * i) - k + 32 - h) % 7;
    int m = (a + (11*h) + (19*l)) / 433;
    int days_to_good_friday = h + l - (7*m) - 2;
    int month = (days_to_good_friday + 90) / 25;
    int day = (days_to_good_friday + (33 * month) + 19) % 32;

    return new DateTime(year, month, day, 0, 0);
  }

  public static DateTime corpusChristi(int year) {
    return goodFriday(year).plusDays(62);
  }
}
