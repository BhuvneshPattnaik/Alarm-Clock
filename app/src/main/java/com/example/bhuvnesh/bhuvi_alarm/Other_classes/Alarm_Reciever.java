package com.example.bhuvnesh.bhuvi_alarm.Other_classes;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by bhuvnesh on 09-01-2017.
 */

public class Alarm_Reciever extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(uri == null)
        {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ring = RingtoneManager.getRingtone(context,uri);
        ring.play();

        ComponentName componentName = new ComponentName(context.getPackageName(),RIngService.class.getName());
        startWakefulService(context,(intent.setComponent(componentName)));
        setResultCode(Activity.RESULT_OK);
    }
}
