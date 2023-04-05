package com.example.refilltracker.Primary;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.refilltracker.Primary.HistoryFill.History;
import com.example.refilltracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotifyReminder extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "NotifyReceive")
                .setSmallIcon(R.drawable.ic_baseline_car_repair_24)
                .setContentTitle("Reminder Fuel and Repair")
                .setContentText("Hey you, more than 10 days you do not load gasoline or repair")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = simpleDateFormat.format(new Date());

    }
}
