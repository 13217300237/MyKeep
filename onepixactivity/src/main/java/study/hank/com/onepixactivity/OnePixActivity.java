package study.hank.com.onepixactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class OnePixActivity extends AppCompatActivity {
    private static final String TAG = "OnePixActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "启动OnePixActivity");

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);

        OnePixKeeper.getInstance().setKeeper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "关闭OnePixActivity");
    }
}
