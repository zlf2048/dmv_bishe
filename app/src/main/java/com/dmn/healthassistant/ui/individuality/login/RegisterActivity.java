package com.dmn.healthassistant.ui.individuality.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.*;
import com.minapp.android.sdk.auth.Auth;
import com.minapp.android.sdk.user.User;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText registerUserEditText,  registerPasswordEditText;
    private TextInputLayout registerUserTextInputLayout;
    private MaterialButton registerMaterialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUserEditText = findViewById(R.id.registerUserEditText);  //用户名
        registerPasswordEditText = findViewById(R.id.registerPasswordEditText);  //密码
        registerUserTextInputLayout = findViewById(R.id.registerUserTextInputLayout);
        registerMaterialButton = findViewById(R.id.registerMaterialButton);  //注册按钮

        registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = registerUserEditText.getText().toString();
                String passwordText = registerPasswordEditText.getText().toString();
                if (usernameText.length() >= 4 && usernameText.length() <= 16 && usernameText.matches("^[a-zA-Z0-9]+$") &&
                        passwordText.length() >= 6 && passwordText.length() <= 16 && passwordText.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$")) {
                    // 用户名和密码都符合要求
                    // 执行注册操作
                    final Handler handler = new Handler(Looper.getMainLooper());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                User user = Auth.signUpByUsername(usernameText, passwordText);
                                //传递登录状态，表明已登录
                                LogUtil.loginStatus(RegisterActivity.this);
                                //保存注册用户信息
                                LoginInfo loginInfo = new LoginInfo(RegisterActivity.this);
                                loginInfo.saveLoginInfo(user);

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                finish();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } catch (Exception e) {
                                System.out.println(e);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "注册失败，请重试", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                } else {
                    // 用户名或密码不符合要求
                    if (!(usernameText.length() >= 4 && usernameText.length() <= 16 && usernameText.matches("^[a-zA-Z0-9]+$"))) {
                        Toast.makeText(RegisterActivity.this, "用户名必须为4-16位字母和数字组合", Toast.LENGTH_SHORT).show();
                    }
                    if (!(passwordText.length() >= 6 && passwordText.length() <= 16 && passwordText.matches("^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+$"))) {
                        Toast.makeText(RegisterActivity.this, "密码必须为6-16位字母、数字、特殊符号组合", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}