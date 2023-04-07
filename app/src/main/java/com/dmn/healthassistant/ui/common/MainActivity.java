package com.dmn.healthassistant.ui.common;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.*;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.IndividualityUnloginFragment;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new SportFragment());

        LayoutInflater inflater = LayoutInflater.from(this);
        View mView = inflater.inflate(R.layout.fragment_bottom_nav,null);
        MaterialButton individuality = mView.findViewById(R.id.individuality);

//        individuality.setTextColor(getResources().getColor(R.color.ripple_color));
//        individuality.setCompoundDrawablesRelativeWithIntrinsicBounds(null, getDrawable(R.drawable.jiankang2), null ,null);


    }

    public void onClick_individuality(View view) {
        System.out.println("nmsl");
        replaceFragment(new IndividualityUnloginFragment());
    }
    public void onClick_sport(View view) {
        System.out.println("nmsl");
        replaceFragment(new SportFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.view,fragment);
        fragmentTransaction.replace(R.id.view, fragment);
        fragmentTransaction.commit();
    }

    private void removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }
}