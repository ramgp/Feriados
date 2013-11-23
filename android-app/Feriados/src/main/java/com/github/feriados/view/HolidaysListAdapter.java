/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Ramon Garcia-Perez, http://about.me/ramgp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.feriados.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.feriados.R;
import com.github.feriados.common.ViewHolder;
import com.github.feriados.model.Holiday;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class HolidaysListAdapter extends BaseAdapter {
  private final Context context;
  private final List<Holiday> holidays;

  public HolidaysListAdapter(Activity context, List<Holiday> holidays) {
    this.context = context;
    this.holidays = holidays;
  }

  @Override
  public int getCount() {
    return holidays.size();
  }

  @Override
  public Holiday getItem(int position) {
    return holidays.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(context)
          .inflate(R.layout.list_item_holiday, parent, false);
    }

    final Holiday holiday = holidays.get(position);

    // TODO: Please make month a three letter identification instead of number consider using "locale"
    String month = holiday.getDate().substring(5, 7);

    LocalDate date = LocalDate.parse(holiday.getDate());
    String monthName = date.toString("MMM").toUpperCase();

    int colorResourceId = context.getResources().getIdentifier("month_" + month,
        "color", context.getPackageName());

    String day = holiday.getDate().substring(8, 10);

    RelativeLayout dayMonthContainer = ViewHolder.getView(convertView, R.id.dayMonthContainer);
    dayMonthContainer.setBackgroundResource(colorResourceId);

    TextView monthTextView = ViewHolder.getView(convertView, R.id.monthTextView);
    monthTextView.setText(monthName);

    TextView dayTextView = ViewHolder.getView(convertView, R.id.dayTextView);
    dayTextView.setText(day);

    TextView dateTextView = ViewHolder.getView(convertView, R.id.holidayDate);
    dateTextView.setText(holiday.getDate());

    TextView reasonTextView = ViewHolder.getView(convertView, R.id.holidayReason);
    reasonTextView.setText(holiday.getReason());

    return convertView;
  }
}