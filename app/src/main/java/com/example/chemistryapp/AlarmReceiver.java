package com.example.chemistryapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("appMessage");

        Intent t = new Intent(context,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,t,0);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("LEARN SOME CHEMISTRY!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
        if (Build.VERSION.SDK_INT < 16) {
            notificationManager.notify(notificationId,builder.getNotification());
        }
        else{
            notificationManager.notify(notificationId,builder.build() );
        }
    }
}
