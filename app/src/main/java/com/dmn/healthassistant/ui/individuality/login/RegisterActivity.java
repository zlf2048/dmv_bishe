package com.dmn.healthassistant.ui.individuality.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.*;
import android.view.View;
import android.widget.TextView;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.*;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText registerUserEditText, phoneNumberEditText, passwordEditText;
    private TextInputLayout registerUserTextInputLayout;
    private MaterialButton registerMaterialButton, getVerificationCodeMaterialButton;
    private TextView findPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUserEditText = findViewById(R.id.registerUserEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerUserTextInputLayout = findViewById(R.id.registerUserTextInputLayout);
        registerMaterialButton = findViewById(R.id.registerMaterialButton);
        getVerificationCodeMaterialButton = findViewById(R.id.getVerificationCodeMaterialButton);
        findPassword = findViewById(R.id.findPassword);

        registerUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textCount = registerUserEditText.getText().length();

                LogUtil.d("RegisterActivity", "" + textCount);

                switch(textCount) {
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
            public void afterTextChanged(Editable s) {}
        });

        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 这里实现提示手机号是否可用的逻辑，不要忘记处理当手机号已被使用时找回密码的逻辑
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        registerMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里实现注册逻辑
            }
        });

        getVerificationCodeMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取验证码
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 处理密码是否符合格式要求逻辑
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent(this, ForgetPasswordActivity.class);
//                startActivity(intent);
            }
        });
    }
}