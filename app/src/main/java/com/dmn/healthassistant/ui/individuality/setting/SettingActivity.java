package com.dmn.healthassistant.ui.individuality.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;

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
                unLoginStatus();
                Toast.makeText(SettingActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void unLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("isLogin");
        editor.apply();
    }
}