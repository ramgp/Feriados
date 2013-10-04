package com.github.feriados.models;

import com.github.feriados.utils.ChristianHolidays;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    int currentYear = DateTime.now().getYear();
    addHolidaysIn(currentYear, holidays);
  }

  private void addHolidaysIn(int currentYear, Map<Integer, Map<String, Holiday>> holidays) {
    Map<String, Holiday> yearHolidays = new LinkedHashMap<String, Holiday>();

    // TODO: Check for the order of the holidays
    for (Holiday holiday : createDefaultHolidays(currentYear)) {
      yearHolidays.put(holiday.getDate(), holiday);
    }

    holidays.put(currentYear, yearHolidays);
  }

  @Override
  public Holiday closestHolidayTo(String date) {
    if (date == null) {
      throw new IllegalArgumentException("date");
    }

    DateTime currentDate = DateTime.parse(date, DEFAULT_DATE_FORMAT);
    return getClosestHoliday(currentDate);
  }

  private Holiday getClosestHoliday(DateTime currentDate) {
    for (Holiday holiday : holidays.get(currentDate.getYear()).values()) {
      if (DateTime.parse(holiday.getDate(), DEFAULT_DATE_FORMAT).isEqual(currentDate) ||
          DateTime.parse(holiday.getDate(), DEFAULT_DATE_FORMAT).isAfter(currentDate)) {
        return holiday;
      }
    }

    int nextYear = currentDate.getYear() + 1;
    return holidays.get(nextYear).get(nextYear + "01-01");
  }

  @Override
  public List<Holiday> holidaysFor(int year) {
    return new ArrayList<Holiday>(holidays.get(year).values());
  }

  @Override
  public Holiday holidayFor(String date) {
    // TODO: Validate the date parameter here
    int yearInDate = Integer.parseInt(date.substring(0, 4));
    return holidays.get(yearInDate).get(date);
  }

  private List<Holiday> createDefaultHolidays(int year) {
    List<Holiday> defaultHolidays = new ArrayList<Holiday>();

    String goodFriday = ChristianHolidays.goodFriday(year).toString(DEFAULT_DATE_FORMAT);
    String corpusChristi = ChristianHolidays.corpusChristi(year).toString(DEFAULT_DATE_FORMAT);

    defaultHolidays.add(createHoliday(year + "-01-01", "D\u00EDa de A\u00F1o Nuevo"));
    defaultHolidays.add(createHoliday(year + "-01-06", "D\u00EDa de los Santos Reyes"));
    defaultHolidays.add(createHoliday(year + "-01-21", "D\u00EDa de la Altagracia"));
    defaultHolidays.add(createHoliday(year + "-01-26", "D\u00EDa de Duarte"));
    defaultHolidays.add(createHoliday(year + "-02-27", "D\u00EDa de la Independencia"));
    defaultHolidays.add(createHoliday(goodFriday, "Viernes Santo"));
    defaultHolidays.add(createHoliday(year + "-05-01", "D\u00EDa del Trabajo"));
    defaultHolidays.add(createHoliday(corpusChristi, "D\u00EDa de Corpus Christi"));
    defaultHolidays.add(createHoliday(year + "-08-16", "D\u00EDa de la Restauraci\u00F3n"));
    defaultHolidays.add(createHoliday(year + "-09-24", "D\u00EDa de las Mercedes"));
    defaultHolidays.add(createHoliday(year + "-11-06", "D\u00EDa de la Constituci\u00F3n"));
    defaultHolidays.add(createHoliday(year + "-12-25", "D\u00EDa de Navidad"));

    return defaultHolidays;
  }

  private Holiday createHoliday(String date, String reason) {
    return new Holiday(date, reason);
  }
}
