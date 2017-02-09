package com.example.apc.punchvillain;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sally on 2017/1/prop1.
 */

public class Utils  {

    public static void galleryAddPic(@NonNull Uri uri) {
        App.context().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
    }

    public static boolean isIntentSafe(@NonNull Intent intent) {
        return isIntentSafe(intent, 0);
    }

    public static boolean isIntentSafe(@NonNull Intent intent, @StringRes int errorRes) {
        Context context = App.context();
        List activities = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        boolean safe = activities.size() > 0;
        if (!safe && errorRes > 0) {
            Toast.makeText(context, errorRes, Toast.LENGTH_LONG).show();
        }
        return safe;
    }
    }

