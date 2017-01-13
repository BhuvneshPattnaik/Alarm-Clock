package com.example.bhuvnesh.bhuvi_alarm.Other_classes;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.bhuvnesh.bhuvi_alarm.R;
import com.example.bhuvnesh.bhuvi_alarm.activity.MainActivity;

/**
 * Created by bhuvnesh on 10-01-2017.
 */

public class RIngService extends IntentService {

    private NotificationManager alarm;

    public RIngService() {
        super("Alarm");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotify("Its Bhuvi's Alarm");
    }

    private void sendNotify(String s) {
        alarm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class),0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setContentTitle("Alarm is Ringing").setSmallIcon(R.drawable.alarm).setStyle(new  NotificationCompat.BigTextStyle().bigText(s)).setContentText(s);
        builder.setContentIntent(pendIntent);
        alarm.notify(1,builder.build());
    }
}
