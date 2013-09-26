package com.github.feriados;

import android.app.Activity;
import android.os.Bundle;
import com.github.feriados.models.HolidayService;

public class MainActivity extends Activity
{
    private String nextHoliday;
    private HolidayService holidayService;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
