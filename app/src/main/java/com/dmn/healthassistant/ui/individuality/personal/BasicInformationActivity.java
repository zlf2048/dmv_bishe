package com.dmn.healthassistant.ui.individuality.personal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Userinfo;
import com.dmn.healthassistant.ui.common.MainActivity;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.minapp.android.sdk.auth.Auth;
import com.minapp.android.sdk.auth.model.UpdateUserReq;
import com.minapp.android.sdk.database.Record;
import com.minapp.android.sdk.database.Table;
import com.minapp.android.sdk.user.User;

public class BasicInformationActivity extends AppCompatActivity {
    private TextInputEditText nickname, username, sex, city, email;
    private String nicknameText, usernameText, sexText, cityText, emailText;

    private MaterialButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        //找到控件
        nickname = findViewById(R.id.nickname);
        username = findViewById(R.id.username);
        save = findViewById(R.id.saveMaterialButton);
        sex = findViewById(R.id.sex);
        city = findViewById(R.id.city);
        email = findViewById(R.id.email);

        //获取登录用户的ID(ID不在页面中显示）
        LoginInfo loginInfo = new LoginInfo(BasicInformationActivity.this);
        String id = loginInfo.getLoginInfo().getId();

        //获取并显示已有的个人信息
        nicknameText = loginInfo.getLoginInfo().getNickname();
        usernameText = loginInfo.getLoginInfo().getUsername();
        sexText = genderString(loginInfo.getLoginInfo().getGender());
        cityText = loginInfo.getLoginInfo().getCity();
        emailText = loginInfo.getLoginInfo().getEmail();

        username.setText(usernameText);
        nickname.setText(nicknameText);
        sex.setText(sexText);
        city.setText(cityText);
        email.setText(emailText);

        //设置性别选择器
        String[] sexArry = {"男","保密","女"};
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(BasicInformationActivity.this);// 自定义对话框
                // checkedItem默认的选中 setSingleChoiceItems设置单选按钮组
                builder3.setSingleChoiceItems(sexArry, 1, (dialog, which) -> {// which是被选中的位置
                    // showToast(which+"");
                    sex.setText(sexArry[which]);
                    dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
                });
                builder3.show();// 让弹出框显示
            }
        });


        //设置保存按钮点击事件
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取各输入框的值
                String nicknameInputText = nickname.getText().toString();
                Integer sexInputText = genderCode(sex.getText().toString());
                String cityInputText = city.getText().toString();
                String emailInputText = email.getText().toString();

                //存入到本地数据库
                Userinfo userinfo = new Userinfo(id, nicknameInputText, usernameText, sexInputText, cityInputText, emailInputText);
                loginInfo.updateLoginInfo(userinfo);

                //存到远程服务器
                Table userprofile = new Table("_userprofile");
                Record record = userprofile.fetchWithoutData(id);
                record.put("nickname",nicknameInputText);
                record.put("gender", sexInputText);
                record.put("city",cityInputText);

                final Handler handler = new Handler(Looper.getMainLooper());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            record.save();
                            //单独请求修改Email   妹大美女啦啦啦啦啦啦
                            UpdateUserReq request = new UpdateUserReq();
                            request.setEmail(emailInputText);
                            Auth.currentUser().updateUser(request);

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
    public static Integer genderCode(String gender) {
        if (gender.equals("男")) {
            return 1;
        } else if (gender.equals("女")) {
            return 2;
        } else{
            return 0;
        }
    }

    public static String genderString(int code) {
        if (code == 1) {
            return "男";
        } else if (code == 2) {
            return "女";
        } else {
            return "保密";
        }
    }
}