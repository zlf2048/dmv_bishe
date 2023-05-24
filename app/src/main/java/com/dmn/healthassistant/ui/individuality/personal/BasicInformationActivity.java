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
        Table userprofile = new Table("_userprofile");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    User user = Auth.currentUser();
                    Userinfo userinfo = new Userinfo(user.getId(), user.getNickname(), user.getUsername(), user.getGender(), user.getCity(), user.getEmail());
                    loginInfo.updateLoginInfo(userinfo);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }).start();
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
                            //单独请求修改Email
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
                            System.out.println(e);
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

//首先定义了一个名为BasicInformationActivity的类。这个类用于处理用户基本信息的显示和修改。
//1.在onCreate方法中，首先通过调用setContentView(R.layout.activity_basic_information)来设置布局。然后，它使用findViewById方法来获取布局中的各个组件，包括昵称、用户名、性别、城市、电子邮件的输入框和保存按钮等。
//2.接下来，获取登录用户的ID，并在新线程中调用相应的方法来获取用户的基本信息。然后，代码将这些信息显示在相应的输入框中。
//3.然后，为性别输入框添加了一个点击监听器。当用户点击性别输入框时，代码会弹出一个单选对话框，让用户选择性别。
//4.最后，为保存按钮添加了一个点击监听器。当用户点击保存按钮时，代码会获取各个输入框的值，并将它们保存到后台服务器中。
//5.此外，还定义了两个静态方法：genderCode和genderString。这两个方法分别用于将性别字符串转换为整数代码和将整数代码转换为性别字符串。
