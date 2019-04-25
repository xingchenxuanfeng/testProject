package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dynamicCmd.DynamicCmdManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDelegate.sApplication = getApplicationContext();

        DynamicCmdManager.getInstance().invoke();
    }
}
