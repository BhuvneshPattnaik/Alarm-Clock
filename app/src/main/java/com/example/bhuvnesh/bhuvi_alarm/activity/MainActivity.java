package com.example.bhuvnesh.bhuvi_alarm.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.bhuvnesh.bhuvi_alarm.Other_classes.Alarm_Reciever;
import com.example.bhuvnesh.bhuvi_alarm.R;
import com.example.bhuvnesh.bhuvi_alarm.fragments.Alarm_1;
import com.example.bhuvnesh.bhuvi_alarm.fragments.Alarm_2;
import com.example.bhuvnesh.bhuvi_alarm.fragments.Clock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static MainActivity activity;
    AlarmManager alarmManager;
    TimePicker timePicker1, timePicker2;
    PendingIntent pendingIntent;
    Intent intent;
    final Calendar calendar = Calendar.getInstance();

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {R.mipmap.ic_access_time_white_24dp, R.mipmap.ic_access_alarm_white_24dp, R.mipmap.ic_access_alarm_white_24dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, Alarm_Reciever.class);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker2 = (TimePicker) findViewById(R.id.timePicker2);

    }

    public static MainActivity ins()
    {
        return activity;
    }

    public void change1(View vi)
    {

            boolean check = ((ToggleButton)vi).isChecked();
            if(check)
            {
                int hr = 0;
                int min = 0;
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                   min = timePicker1.getMinute();
                   hr = timePicker1.getHour();
               }
                else
               {
                   hr = timePicker1.getCurrentHour();
                   min = timePicker1.getCurrentMinute();
               }
                calendar.set(Calendar.HOUR_OF_DAY, hr);
                calendar.set(Calendar.MINUTE, min);
                String hour = String.valueOf(hr);
                String minute = String.valueOf(min);
                if(hr > 12)
                {
                    hour = String.valueOf(hr - 12);
                }
                if(min < 10)
                {
                    minute = "0" + String.valueOf(min);
                }

                Toast.makeText(getApplicationContext(),"Alarm set to "+hour+":"+minute,Toast.LENGTH_LONG).show();
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
            }
            else
            {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(getApplicationContext(),"Alarm is off now",Toast.LENGTH_SHORT).show();
            }
        }

    public void change2(View vi)
    {

        boolean check = ((ToggleButton)vi).isChecked();
        if(check)
        {
            int hr = 0;
            int min = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                min = timePicker2.getMinute();
                hr = timePicker2.getHour();
            }
            else
            {
                hr = timePicker2.getCurrentHour();
                min = timePicker2.getCurrentMinute();
            }
            calendar.set(Calendar.HOUR_OF_DAY, hr);
            calendar.set(Calendar.MINUTE, min);
            String hour = String.valueOf(hr);
            String minute = String.valueOf(min);
            if(hr > 12)
            {
                hour = String.valueOf(hr - 12);
            }
            if(min < 10)
            {
                minute = "0" + String.valueOf(min);
            }

            Toast.makeText(getApplicationContext(),"Alarm set to "+hour+":"+minute,Toast.LENGTH_LONG).show();
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
        }
        else
        {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(getApplicationContext(),"Alarm is off now",Toast.LENGTH_SHORT).show();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Clock(), "CLOCK");
        adapter.addFragment(new Alarm_1(), "ALARM 1");
        adapter.addFragment(new Alarm_2(), "ALARM 2");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
