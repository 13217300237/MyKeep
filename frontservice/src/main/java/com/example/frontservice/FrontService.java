package com.example.frontservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class FrontService extends Service {

    private static final String TAG = "ForegroundService";
    private static final int SERVICE_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "服务创建了");


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {//4.3以下
            startForeground(SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {//4.3-7.0
            startForeground(SERVICE_ID, new Notification());//将service设置成前台服务
            startService(new Intent(this, InnerService.class));//删除通知栏消息
            //这里其实是谷歌的一个bug，你开启两个相同的前台service，然后关闭其中一个，则通知栏的消息就会消失
        } else {//8.0以上，建立了新的规则
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("channel", "xx", NotificationManager.IMPORTANCE_NONE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this, "channel").build();
                startForeground(SERVICE_ID, notification);
            }
            //暂时没有办法隐藏通知栏里的消息
        }
    }

    private class InnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            Log.e(TAG, "InnerService 服务创建了");
            startForeground(SERVICE_ID, new Notification());
            stopSelf();
        }
    }
}
