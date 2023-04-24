package com.dmn.healthassistant.ui.individuality.login;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.minapp.android.sdk.auth.Auth;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.user.User;

public class LoginActivity extends AppCompatActivity {
    private TextView register;
    private MaterialButton loginMaterialButton;
    private CheckBox rememberPasswordCheckBox;
    private TextInputEditText userName, accountPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        loginMaterialButton = findViewById(R.id.loginMaterialButton);
        userName = findViewById(R.id.userName);
        accountPassword = findViewById(R.id.accountPassword);
        rememberPasswordCheckBox = findViewById(R.id.rememberPasswordCheckBox);

        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        userName.setText(sharedPreferences.getString("username",""));
        accountPassword.setText(sharedPreferences.getString("password",""));

        //点击新用户注册，跳转到注册界面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //请求登录
        loginMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameText = userName.getText().toString();
                String accountPasswordText = accountPassword.getText().toString();

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            User user;
                            user = Auth.signInByUsername(userNameText, accountPasswordText);
                            //传递登录状态，表明已登录
                            LogUtil.loginStatus(LoginActivity.this);
                            //保存登录用户信息
                            LoginInfo loginInfo = new LoginInfo(LoginActivity.this);
                            loginInfo.saveLoginInfo(user);
                            //提示登录成功
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            rememberPassword(rememberPasswordCheckBox.isChecked(), userNameText, accountPasswordText);
                            //关闭登录页面，跳转到MainActivity
                            finish();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "用户名或密码有误，请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

//        测试数据库操作（CRUD）
//        new Thread(new Runnable(){
//                    @Override
//                    public void run() {
//
//                        Table product = new Table("product");//操作表“product"
//                        Record record = product.createRecord();//弄一个record对象，对应一条数据，比如表头是姓名年龄， xxy 21 就是一条数据
//                        record.put("name","张同学");
//                        try {
//                            record.save();
//                        }catch (Exception e){
//                            System.out.println("CRUD异常了");
//                            System.out.println(e);
//                        }
//                    }
//                }).start();

//                if ((userNameText.equals("15295751665") && accountPasswordText.equals("123456"))) {
//                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//
//        rememberPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // Implement remember password logic
//            }
//        });

//        forgetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(this, ForgetPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    public void rememberPassword(Boolean isChecked, String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("remember", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isChecked) {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
        } else {
            editor.remove("username");
            editor.remove("password");
            editor.apply();
        }
    }
}
