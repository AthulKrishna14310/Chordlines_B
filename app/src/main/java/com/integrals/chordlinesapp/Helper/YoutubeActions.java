package com.integrals.chordlinesapp.Helper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;

public class YoutubeActions {
    private Context context;
    private Activity activity;
    public YoutubeActions(Context context,Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    public  void subscribeYoutubeChannel(String id) {
        Snackbar.with(activity,null)
                .type(Type.CUSTOM)
                .message("Loading Youtube Link  link...")
                .duration(Duration.SHORT)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/channel/" + id));
                webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
           context.startActivity(webIntent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context,"Sorry... unable to open Youtube.",Toast.LENGTH_SHORT).show();
        }
    }

    public  void watchYoutubeVideo(String id) {
        Snackbar.with(activity,null)
                .type(Type.CUSTOM)
                .message("Loading Youtube Link  link...")
                .duration(Duration.SHORT)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();

        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(id));
        webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
}
