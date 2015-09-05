package com.example.uros.dnd.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by Uros on 0005 05 Sep.
 */
public class GPSService {

    public static final int REQUEST_CODE = 23;

    public static void turnOnService(Context context) {

        Intent i = new Intent(context, GPSReceiver.class);

        PendingIntent sender = PendingIntent.getBroadcast(context,REQUEST_CODE, i, 0);
        // We want the alarm to go off 3 seconds from now.
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 3 * 1000;//start 3 seconds after first register.
        // Schedule the alarm!
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                60000, sender);//10min interval
    }

    public static void turnOffService(Context context) {
        Intent i = new Intent(context, GPSReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context,REQUEST_CODE, i, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }

}
