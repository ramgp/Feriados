package com.github.feriados.models;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ramon
 * Date: 9/25/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HolidaysService
{
  Holiday closestHolidayTo(String year);
  List<Holiday> holidaysFor(int year);
  Holiday holidayFor(String day);
}
