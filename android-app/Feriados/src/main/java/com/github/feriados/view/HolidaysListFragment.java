package com.github.feriados.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.feriados.R;
import com.github.feriados.model.DefaultHolidaysService;
import com.github.feriados.model.Holiday;
import com.github.feriados.model.HolidaysService;

import org.joda.time.DateTime;

import java.util.List;

public class HolidaysListFragment extends Fragment {

  private HolidaysService holidaysService;

  public HolidaysListFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_holiday_list, container, false);

    Bundle args = getArguments();

    int year = DateTime.now().getYear();

    if (args != null) {
      year = args.getInt("year");
    }

    holidaysService = new DefaultHolidaysService();
    List<Holiday> holidays = holidaysService.holidaysFor(year);

    final HolidaysListAdapter adapter = new HolidaysListAdapter(getActivity(), holidays);
    ListView list = (ListView) view.findViewById(R.id.list_view);
    list.setAdapter(adapter);

    // If the user clicks on a talk, show the details activity with its info.
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Holiday holiday = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), HolidayActivity.class);
        intent.putExtra(HolidayDetailFragment.HOLIDAY_DATE, holiday.getDate());
        startActivity(intent);
      }
    });

    return view;
  }
}
