package com.github.feriados.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.github.feriados.R;

/**
 * An activity representing a single Holiday detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MainActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link HolidayDetailFragment}.
 */
public class HolidayActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_holiday);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putString(HolidayDetailFragment.HOLIDAY_DATE,
          getIntent().getStringExtra(HolidayDetailFragment.HOLIDAY_DATE));

      HolidayDetailFragment fragment = new HolidayDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.holiday_details_fragment_container, fragment)
          .commit();
    }
  }
}
