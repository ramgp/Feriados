package com.github.feriados;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.feriados.models.Holiday;

import java.util.List;

/**
 * Created by ramgp on 10/3/13.
 */
public class HolidaysArrayAdapter extends ArrayAdapter<Holiday> {
  private final Activity context;
  private final List<Holiday> holidays;
  private final int layout;

  static class ViewHolder {
    public TextView holidayDate;
    public TextView holidayReason;
  }

  public HolidaysArrayAdapter(Activity context, int layout, List<Holiday> holidays) {
    super(context, layout, holidays);
    this.context = context;
    this.layout = layout;
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
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View rowView = convertView;

    if (rowView == null) {
      LayoutInflater inflater = context.getLayoutInflater();
      rowView = inflater.inflate(layout, null);
      ViewHolder viewHolder = new ViewHolder();

      if (layout == R.layout.holiday_row) {
        viewHolder.holidayDate = (TextView) rowView.findViewById(R.id.holidayDate);
        viewHolder.holidayReason = (TextView) rowView.findViewById(R.id.holidayReason);
      } else {
        viewHolder.holidayDate = (TextView) rowView.findViewById(android.R.id.text2);
        viewHolder.holidayReason = (TextView) rowView.findViewById(android.R.id.text1);
      }

      rowView.setTag(viewHolder);
    }

    ViewHolder viewHolder = (ViewHolder) rowView.getTag();
    viewHolder.holidayDate.setText(holidays.get(position).getDate());
    viewHolder.holidayReason.setText(holidays.get(position).getReason());

    return rowView;
  }
}