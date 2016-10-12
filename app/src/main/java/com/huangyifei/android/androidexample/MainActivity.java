package com.huangyifei.android.androidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huangyifei.android.androidexample.savestate.ActivityOne;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSaveState(View view) {
        ActivityOne.launch(this);
    }
}
