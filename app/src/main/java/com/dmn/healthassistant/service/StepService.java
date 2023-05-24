package com.dmn.healthassistant.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class StepService extends Service implements SensorEventListener {
    //动态申请健康运动权限

    private final String TAG = "TDSSS";
    SensorManager mSensorManager;
    Sensor stepDetector;

    int mSteps = 0;
    private boolean isRunning = true;

    @Override
    public void onCreate() {
        super.onCreate();
        // 在这里初始化服务
        // 获取SensorManager管理器实例
        System.out.println("已启动服务");
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // 获取计步器sensor
        stepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 在这里处理服务的启动逻辑
        if(stepDetector != null){
            // 如果sensor找到，则注册监听器
            mSensorManager.registerListener(this, stepDetector, 1000000);
            Log.e(TAG,"Detector sensor found");
        }
        else{
            Log.e(TAG,"no step Detector sensor found");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在这里处理服务的销毁逻辑
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 如果服务不需要与其他组件进行通信，可以返回null
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 在这里处理传感器数据变化事件
        if (event.values[0] == 1){
            mSteps++;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("step_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("steps", mSteps);
        editor.commit();
        Log.i(TAG,"Detected step changes:"+event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 在这里处理传感器精度变化事件
    }
}

//这段代码定义了一个名为 StepService 的类，它继承自 Service 类并实现了 SensorEventListener 接口，用于实现计步功能。
//
//在 onCreate 方法中，首先通过 getSystemService 方法获取了 SensorManager 管理器实例。然后通过 getDefaultSensor 方法获取了计步器 sensor。
//
//在 onStartCommand 方法中，判断计步器 sensor 是否存在。如果存在，则调用 registerListener 方法注册监听器，监听计步器 sensor 的数据变化。
//
//在 onDestroy 方法中，如果 SensorManager 管理器实例不为空，则调用 unregisterListener 方法取消监听器的注册。
//
//在 onBind 方法中，返回了 null，表示该服务不需要与其他组件进行通信。
//
//在 onSensorChanged 方法中，处理传感器数据变化事件。当 event.values[0] 等于 1 时，表示检测到一步，将 mSteps 变量加 1。然后将 mSteps 变量的值保存到 SharedPreferences 中。
//
//在 onAccuracyChanged 方法中，处理传感器精度变化事件。但该方法并未实现任何功能
