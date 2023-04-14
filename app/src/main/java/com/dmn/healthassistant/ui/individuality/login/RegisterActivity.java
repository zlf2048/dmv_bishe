package com.dmn.healthassistant.ui.individuality.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.*;
import com.minapp.android.sdk.auth.Auth;

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

        registerUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textCount = registerUserEditText.getText().length();

                LogUtil.d("RegisterActivity", "" + textCount);

                switch (textCount) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 17:
                    case 18:
                        registerUserTextInputLayout.setBoxStrokeColor(getResources().getColor(R.color.error_color));
                        registerUserTextInputLayout.setEndIconDrawable(getDrawable(R.drawable.cuowu));
                        registerUserTextInputLayout.setEndIconTintList(getResources().getColorStateList(R.color.error_color));
                        break;
                    default:
                        registerUserTextInputLayout.setBoxStrokeColor(getResources().getColor(R.color.ripple_color));
                        // registerUserTextInputLayout.setEndIconDrawable(getDrawable(R.drawable.zhengque));
                        // registerUserTextInputLayout.setEndIconTintList(getResources().getColorStateList(R.color.ripple_color));
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // 这里实现提示手机号是否可用的逻辑，不要忘记处理当手机号已被使用时找回密码的逻辑
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });

        registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里实现注册逻辑
            }
        });

//        getVerificationCodeMaterialButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 获取验证码
//            }
//        });

        registerPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 处理密码是否符合格式要求逻辑
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

//        findPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
////                Intent intent = new Intent(this, ForgetPasswordActivity.class);
////                startActivity(intent);
//            }
//        });


        //请求注册
        registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = registerUserEditText.getText().toString();
                String passwordText = registerPasswordEditText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Auth.signInByUsername(userNameText, accountPasswordText);
                            Auth.signUpByUsername(usernameText, passwordText);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }).start();


                //传递登录状态，表明已登录
                loginStatus();
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    //设置为“已登录”
    public void loginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();

    }

}