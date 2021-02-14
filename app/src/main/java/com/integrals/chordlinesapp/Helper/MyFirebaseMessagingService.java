package com.integrals.chordlinesapp.Helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.integrals.chordlinesapp.MainActivity;
import com.integrals.chordlinesapp.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationHelper notificationHelper;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Resources res = getApplicationContext().getResources();
            int id = R.drawable.logo_chordlines__;
            Bitmap bitmap= BitmapFactory.decodeResource(res, id);

            notificationHelper=new NotificationHelper(getApplicationContext());
            Notification.Builder builder=notificationHelper.buildChordlineNotification(
                    89,
                    remoteMessage.getNotification().getBody(),
                    intent,
                    pendingIntent
            );
            builder.setSmallIcon(R.drawable.ic_notification_icon);
            builder.setLargeIcon(bitmap);
            builder.setAutoCancel(true);
            notificationHelper.getNotificationManager().notify(89,builder.build());

        }else {


            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            notificationBuilder.setContentTitle("Chordlines notification");
            notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
            notificationBuilder.setAutoCancel(true);
            notificationBuilder.setSmallIcon(R.drawable.ic_notification_icon);
            notificationBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(890, notificationBuilder.build());


        }

    }
}
