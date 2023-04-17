package com.dmn.healthassistant.ui.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.*;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.diet.DietFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityLoginFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityUnloginFragment;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new SportFragment()); //启动时默认Sport界面

        LayoutInflater inflater = LayoutInflater.from(this);
        View mView = inflater.inflate(R.layout.fragment_bottom_nav,null);
        MaterialButton individuality = mView.findViewById(R.id.individuality);//声明并找到质感按钮individuality

//        individuality.setTextColor(getResources().getColor(R.color.ripple_color));
//        individuality.setCompoundDrawablesRelativeWithIntrinsicBounds(null, getDrawable(R.drawable.jiankang2), null ,null);


    }
    //点人头，有未登录和已登录两个页面。如果已登录，跳转到已登录界面；如果未登录，跳转到未登录界面
    public void onClick_individuality(View view) {
        if (LogUtil.judgeLogin(MainActivity.this)) {
            replaceFragment(new IndividualityLoginFragment());
        } else {
            replaceFragment(new IndividualityUnloginFragment());
        }
//        replaceFragment(new IndividualityUnloginFragment());
    }


    public void onClick_sport(View view) {
        replaceFragment(new SportFragment());
    }
    public void onClick_diet(View view) {
        replaceFragment(new DietFragment());
    }

    //定义replaceFragment要做什么，上面直接调用
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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