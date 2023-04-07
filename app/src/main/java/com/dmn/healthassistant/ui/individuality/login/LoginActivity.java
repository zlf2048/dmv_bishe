package com.dmn.healthassistant.ui.individuality.login;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.dmn.healthassistant.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextView register, forgetPassword;
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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userNameText = userName.getText().toString();
                String accountPasswordText = accountPassword.getText().toString();
                if ((userNameText.equals("15295751665") && accountPasswordText.equals("123456"))) {
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
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
}