package com.dmn.healthassistant.ui.common;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dmn.healthassistant.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //延时操作
        Timer timer = new Timer();
        timer.schedule(timetast,3000);
    }
        TimerTask timetast = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,MainActivity.class));
            }
        };
}
