package com.example.chemistryapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("appMessage");
        Uri sound;

        if(intent.getStringExtra("soundUri").equals("None")){
            sound = null;
        }
        else{
            sound = Uri.parse(intent.getStringExtra("soundUri"));
        }

        Intent t = new Intent(context,MainActivity.class);
        t.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIntent = PendingIntent.getActivity(context,0,t,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        long[] v = {500,1000};

        if(sound == null){
            v[0] = 0;
            v[1] = 0;
        }

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("LEARN SOME CHEMISTRY!")
                .setContentText(message)
                .setAutoCancel(true)
                .setVibrate(v)
                .setSound(sound)
                .setContentIntent(contentIntent);
        if (Build.VERSION.SDK_INT < 16) {
            notificationManager.notify(notificationId,builder.getNotification());
        }
        else{
            notificationManager.notify(notificationId,builder.build() );
        }
    }
}
