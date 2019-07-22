package study.hank.com.onepixactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class OnePixKeeperReceiver extends BroadcastReceiver {

    private static final String TAG = "OnePixKeeperReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action is " + action);

        if (TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)) {// 熄屏，打开1像素Activity
            OnePixKeeper.getInstance().startKeeper(context);
        } else if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {//亮屏，关闭1像素Activity
            OnePixKeeper.getInstance().finishKeeper();
        }
    }
}
