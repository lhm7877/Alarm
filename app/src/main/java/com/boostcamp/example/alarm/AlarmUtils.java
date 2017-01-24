package com.boostcamp.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hooo on 2017-01-24.
 */

public class AlarmUtils {

    public static void  startAlarm(Context context,PendingIntent pendingIntent, int time){
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i("mylog","마시멜로우");
            manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,pendingIntent);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis() + time, pendingIntent);
        }else {
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,pendingIntent);
        }
    }

    public static void repeatAlarm(Context context){
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        startAlarm(context,pendingIntent,5000);
    }
}
