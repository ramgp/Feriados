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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.github.feriados.R;

import org.joda.time.DateTime;

import java.util.Locale;


public class MainActivity extends ActionBarActivity
    implements TabListener {
  private static final int TAB_CURRENT_YEAR = 0;
  private static final int TAB_NEXT_YEAR = 1;
  private static final int TAB_COUNT = 2;

  private ViewPager mViewPager;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final ActionBar actionBar = getSupportActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(sectionsPagerAdapter);

    mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        actionBar.setSelectedNavigationItem(position);
      }
    });

    // Create the tabs for each section.
    for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
      ActionBar.Tab tab = actionBar.newTab();
      tab.setText(sectionsPagerAdapter.getPageTitle(i));
      tab.setTabListener(this);
      actionBar.addTab(tab);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    mViewPager.setCurrentItem(tab.getPosition());
  }

  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

  }

  /**
   * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to one of the sections.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private HolidaysListFragment mCurrentYearFragment = null;
    private HolidaysListFragment mNextYearFragment = null;
    private int mCurrentYear = DateTime.now().getYear();

    public SectionsPagerAdapter(FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      switch (position) {
        case TAB_CURRENT_YEAR: {
          if (mCurrentYearFragment == null) {
            mCurrentYearFragment = new HolidaysListFragment();
            Bundle args = new Bundle();
            args.putInt("year", mCurrentYear);
            mCurrentYearFragment.setArguments(args);
          }
          return mCurrentYearFragment;
        }
        case TAB_NEXT_YEAR: {
          if (mNextYearFragment == null) {
            mNextYearFragment = new HolidaysListFragment();
            Bundle args = new Bundle();
            args.putInt("year", mCurrentYear + 1);
            mNextYearFragment.setArguments(args);
          }
          return mNextYearFragment;
        }
      }
      return null;
    }

    @Override
    public int getCount() {
      return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Locale l = Locale.getDefault();
      switch (position) {
        case TAB_CURRENT_YEAR:
          return "" + mCurrentYear;
        case TAB_NEXT_YEAR:
          return "" + (mCurrentYear + 1);
      }
      return null;
    }
  }
}

