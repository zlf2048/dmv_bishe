package com.dmn.healthassistant.ui.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.*;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.diet.DietFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityLoginFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityUnloginFragment;
import com.dmn.healthassistant.ui.information.InformationFragment;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}