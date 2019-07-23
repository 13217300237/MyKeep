package com.example.stickybroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 由于安卓SDK 5以上都是粘性广播，所以用默认的就可以了
 */
public class StickyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //START_STICKY
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    //广播拉活不用想了，已经废了,安卓，对广播的限制力度越来越大
}
