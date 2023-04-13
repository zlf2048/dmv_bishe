package com.dmn.healthassistant;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.minapp.android.sdk.BaaS;

public class HealthAssistantApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        BaaS.init("f407bf576281c762f07c", this);
        context = getApplicationContext();
    }
}