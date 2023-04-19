package com.dmn.healthassistant.ui.individuality.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Userinfo;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.minapp.android.sdk.auth.Auth;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.user.User;

public class BasicInformationActivity extends AppCompatActivity {
    private TextInputEditText nickname, username;
    private String nicknameText, usernameText;

    private MaterialButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        //找到控件
        nickname = findViewById(R.id.nickname);
        username = findViewById(R.id.username);
        save = findViewById(R.id.saveMaterialButton);

        //获取登录用户的ID(ID不在页面中显示）
        LoginInfo loginInfo = new LoginInfo(BasicInformationActivity.this);
        String id = loginInfo.getLoginInfo().getId();

        //获取并显示已有的个人信息
        nicknameText = loginInfo.getLoginInfo().getNickname();
        usernameText = loginInfo.getLoginInfo().getUsername();
        username.setText(usernameText);
        nickname.setText(nicknameText);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nicknameInputText = nickname.getText().toString();

                Userinfo userinfo = new Userinfo(id, nicknameInputText, usernameText);
                loginInfo.updateLoginInfo(userinfo);

                Table userprofile = new Table("_userprofile");
                Record record = userprofile.fetchWithoutData(id);
                record.put("nickname",nicknameInputText);

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            record.save();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            finish();
                        } catch (Exception e) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "保存失败，请重试", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}