package com.github.feriados.models;

import com.github.feriados.utils.ChristianHolidays;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ramon
 * Date: 9/25/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultHolidaysService implements HolidaysService {

  private Map<Integer, Map<String, Holiday>> holidays;

  final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

  public DefaultHolidaysService() {
    holidays = new HashMap<Integer, Map<String, Holiday>>();
  }

  @Override
  public Holiday closestHolidayTo(String date) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Holiday> holidaysFor(int year) {
    return new ArrayList<Holiday>(holidays.get(year).values());
  }

  @Override
  public Holiday holidayFor(String date) {
    return null;
  }

  private List<Holiday> createDefaultHolidays(int year) {
    List<Holiday> defaultHolidays = new ArrayList<Holiday>();

    String goodFriday = ChristianHolidays.goodFriday(year).toString(DEFAULT_DATE_FORMAT);
    String corpusChristi = ChristianHolidays.corpusChristi(year).toString(DEFAULT_DATE_FORMAT);

    defaultHolidays.add(createHoliday(year + "-01-01", "Día de Año Nuevo"));
    defaultHolidays.add(createHoliday(year + "-01-06", "Día de los Santos Reyes"));
    defaultHolidays.add(createHoliday(year + "-01-21", "Día de la Altagracia"));
    defaultHolidays.add(createHoliday(year + "-01-26", "Día de Duarte"));
    defaultHolidays.add(createHoliday(year + "-02-27", "Día de la Independencia"));
    defaultHolidays.add(createHoliday(goodFriday, "Viernes Santo"));
    defaultHolidays.add(createHoliday(year + "-05-01", "Día del Trabajo"));
    defaultHolidays.add(createHoliday(corpusChristi, "Día de Copus Cristi"));
    defaultHolidays.add(createHoliday(year + "-08-16", "Día de la Restauración"));
    defaultHolidays.add(createHoliday(year + "-09-24", "Día de las Mercedes"));
    defaultHolidays.add(createHoliday(year + "-11-06", "Día de la Constitución"));
    defaultHolidays.add(createHoliday(year + "-12-25", "Día de Navidad"));

    return defaultHolidays;
  }

  private Holiday createHoliday(String date, String reason) {
    return new Holiday(date, reason);
  }
}
