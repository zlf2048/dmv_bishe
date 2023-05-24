package com.dmn.healthassistant.ui.individuality.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.dmn.healthassistant.util.LoginInfo;
import com.minapp.android.sdk.auth.Auth;

public class SettingActivity extends AppCompatActivity {

    private Button mbtnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mbtnLogOut = findViewById(R.id.log_out);
        mbtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler(Looper.getMainLooper());
                //请求退出
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Auth.logout();
                            //传递登录状态，表明退出登录
                            LogUtil.unLoginStatus(SettingActivity.this);
                            //删除登录用户信息
                            LoginInfo loginInfo = new LoginInfo(SettingActivity.this);
                            loginInfo.deleteLoginInfo(loginInfo.getLoginInfo());
                            //提示退出登录
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SettingActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                                }
                            });
                            finish();
                            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(SettingActivity.this, "未登录，不能退出登录", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}

//首先定义了一个名为SettingActivity的类，它继承了AppCompatActivity。这个类用于处理用户退出登录。
//1.在onCreate方法中，代码首先通过调用setContentView(R.layout.activity_setting)来设置布局。然后，它使用findViewById方法来获取布局中的退出登录按钮。
//2.接下来，代码为退出登录按钮添加了一个点击监听器。当用户点击退出登录按钮时，代码会在新线程中调用Auth.logout()方法进行退出登录。如果退出登录成功，代码会删除用户信息并跳转到MainActivity。