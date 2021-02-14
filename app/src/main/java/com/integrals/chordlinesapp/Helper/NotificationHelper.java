package com.integrals.chordlinesapp.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import com.integrals.chordlinesapp.R;

public class NotificationHelper extends ContextWrapper {
    private NotificationManager notificationManager;
    private Bitmap bitmap;

    public NotificationHelper(Context base) {
        super(base);
        Resources res = getApplicationContext().getResources();
        int id = R.drawable.logo_chordlines__;
        bitmap= BitmapFactory.decodeResource(res, id);
        createNotificationChannels();

    }



        private void createNotificationChannels() {
            NotificationChannel notificationChannel = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel("ID_503", "Chordlines ", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.enableLights(true);

                notificationChannel.setSound(null, null);
                notificationChannel.setLightColor(Color.WHITE);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                getNotificationManager().createNotificationChannel(notificationChannel);
//mkmkm   mk
            }


        }


    public NotificationManager getNotificationManager() {
        if(notificationManager==null)
            notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }

    public Notification.Builder buildChordlineNotification(int i, String s,
                                                           Intent intent,
                                                           PendingIntent pendingIntent) {
        Notification.Builder notificationbuilder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationbuilder = (Notification.Builder)
                    new Notification.Builder(getApplicationContext(),"ID_504")
                    .setContentTitle("Chordlines")
                    .setContentText(s)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(bitmap)
                    .setSmallIcon(R.drawable.ic_notification_icon)
                    .setProgress(100, 0,true);
        }

        return notificationbuilder;





        }
}
