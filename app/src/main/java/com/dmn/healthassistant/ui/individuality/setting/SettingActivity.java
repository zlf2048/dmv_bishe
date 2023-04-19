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