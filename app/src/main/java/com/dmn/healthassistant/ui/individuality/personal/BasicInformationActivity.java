package com.dmn.healthassistant.ui.individuality.personal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.model.Userinfo;
import com.dmn.healthassistant.util.LoginInfo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class BasicInformationActivity extends AppCompatActivity {
    private TextInputEditText nickname, username;
    private String nicknameText, usernameText;

    private MaterialButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);

        nickname = findViewById(R.id.nickname);
        username = findViewById(R.id.username);
        save = findViewById(R.id.saveMaterialButton);

        LoginInfo loginInfo = new LoginInfo(BasicInformationActivity.this);
        String id = loginInfo.getLoginInfo().getId();

        nicknameText = loginInfo.getLoginInfo().getNickname();
        usernameText = loginInfo.getLoginInfo().getUsername();
        username.setText(usernameText);
        nickname.setText(nicknameText);

        String nicknameInputText = nickname.getText().toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(nicknameInputText);
//                Userinfo userinfo = new Userinfo(id, nicknameInputText, usernameText);
//                loginInfo.updateLoginInfo(userinfo);
            }
        });
    }
}