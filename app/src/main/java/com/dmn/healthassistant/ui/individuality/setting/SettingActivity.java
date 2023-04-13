package com.dmn.healthassistant.ui.individuality.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dmn.healthassistant.R;

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
                Toast.makeText(SettingActivity.this, "LogOut被点击了！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}