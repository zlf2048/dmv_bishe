package com.dmn.healthassistant.ui.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.*;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.service.StepService;
import com.dmn.healthassistant.ui.diet.DietFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityLoginFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityUnloginFragment;
import com.dmn.healthassistant.ui.information.InformationFragment;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 321;
    private static final String[] ACTIVITY_RECOGNITION_PERMISSION = {Manifest.permission.ACTIVITY_RECOGNITION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, StepService.class);
        startService(intent);
    }
}