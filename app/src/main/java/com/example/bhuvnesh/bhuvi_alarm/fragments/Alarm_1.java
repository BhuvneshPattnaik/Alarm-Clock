package com.example.bhuvnesh.bhuvi_alarm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhuvnesh.bhuvi_alarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Alarm_1 extends Fragment {

    public Alarm_1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newView = inflater.inflate(R.layout.fragment_alarm_1, container, false);
      return newView;
    }

}
