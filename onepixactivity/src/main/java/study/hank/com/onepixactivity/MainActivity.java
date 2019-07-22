package study.hank.com.onepixactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 演示1像素保活
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnePixKeeper.getInstance().registerKeeper(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OnePixKeeper.getInstance().unregisterKeeper(this);
    }
}
