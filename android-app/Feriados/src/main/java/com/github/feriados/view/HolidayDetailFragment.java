package com.github.feriados.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.feriados.R;
import com.github.feriados.model.DefaultHolidaysService;
import com.github.feriados.model.Holiday;

/**
 * A fragment representing a single Holiday detail screen.
 * This fragment is either contained in a {@link MainActivity}
 * in two-pane mode (on tablets) or a {@link HolidayActivity}
 * on handsets.
 */
public class HolidayDetailFragment extends Fragment {

  /**
   * The fragment argument representing the holiday date that this fragment
   * represents.
   */
  public static final String HOLIDAY_DATE = "holidayDate";

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public HolidayDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_holiday, null);

    String selectedHolidayDate = getArguments().getString(HOLIDAY_DATE);
    final Holiday holiday = new DefaultHolidaysService().holidayFor(selectedHolidayDate);

    if (holiday != null) {
      ((TextView) rootView.findViewById(R.id.holiday_reason)).setText(holiday.getReason());
      ((TextView) rootView.findViewById(R.id.holiday_date)).setText(holiday.getDate());
      ((TextView) rootView.findViewById(R.id.descriptionTextView)).setText(holiday.getDescription());
    }

    return rootView;
  }
}
