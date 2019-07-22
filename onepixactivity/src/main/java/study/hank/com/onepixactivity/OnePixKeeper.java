package study.hank.com.onepixactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class OnePixKeeper {
    private static OnePixKeeper instance = new OnePixKeeper();

    private OnePixKeeper() {
    }

    public static OnePixKeeper getInstance() {
        return instance;
    }

    private WeakReference<Activity> weakReference;

    /**
     * 打开1像素保活Activity
     *
     * @param context
     */
    public void startKeeper(Context context) {
        Intent i = new Intent(context, OnePixActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    /**
     * 关闭一像素保活Activity
     */
    public void finishKeeper() {
        if (weakReference != null) {
            Activity activity = weakReference.get();
            if (activity != null) {
                activity.finish();
            }
            weakReference = null;
        }
    }

    //广播
    private OnePixKeeperReceiver onePixKeeperReceiver;

    /**
     * 注册广播监听
     *
     * @param context
     */
    public void registerKeeper(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        onePixKeeperReceiver = new OnePixKeeperReceiver();
        context.registerReceiver(onePixKeeperReceiver, intentFilter);
    }

    /**
     * 注销广播监听
     *
     * @param context
     */
    public void unregisterKeeper(Context context) {
        if (onePixKeeperReceiver != null)
            context.unregisterReceiver(onePixKeeperReceiver);
    }

    /**
     * 设置弱引用，为了到时候关闭它
     * <p>
     * 弱引用，因为这个类是static单例，如果是强引用，有可能导致泄漏
     *
     * @param onePixActivity
     */
    public void setKeeper(OnePixActivity onePixActivity) {
        weakReference = new WeakReference<Activity>(onePixActivity);
    }
}
