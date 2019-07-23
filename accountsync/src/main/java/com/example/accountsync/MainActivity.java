package com.example.accountsync;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.accountsync.account.AccountHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccountHelper.addAccount(this);
        AccountHelper.autoSync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
