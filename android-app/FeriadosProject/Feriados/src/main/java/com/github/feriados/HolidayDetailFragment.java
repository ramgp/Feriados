package com.github.feriados;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.feriados.models.DefaultHolidaysService;
import com.github.feriados.models.Holiday;
import com.github.feriados.models.HolidaysService;

/**
 * A fragment representing a single Holiday detail screen.
 * This fragment is either contained in a {@link HolidayListActivity}
 * in two-pane mode (on tablets) or a {@link HolidayDetailActivity}
 * on handsets.
 */
public class HolidayDetailFragment extends Fragment {

  private HolidaysService holidaysService;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String HOLIDAY_DATE = "getDate()";

    /**
     * The dummy content this fragment is presenting.
     */
    private Holiday holiday;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HolidayDetailFragment() {
      holidaysService = new DefaultHolidaysService();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(HOLIDAY_DATE)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            holiday = holidaysService.holidayFor(getArguments().getString(HOLIDAY_DATE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_holiday, container, false);

        // Show the dummy content as text in a TextView.
        if (holiday != null) {
            ((TextView) rootView.findViewById(R.id.holiday_reason)).setText(holiday.getReason());
            ((TextView) rootView.findViewById(R.id.holiday_date)).setText(holiday.getDate());
        }

        return rootView;
    }
}
