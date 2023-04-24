package com.dmn.healthassistant.ui.common;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.diet.DietFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityLoginFragment;
import com.dmn.healthassistant.ui.individuality.IndividualityUnloginFragment;
import com.dmn.healthassistant.ui.information.InformationFragment;
import com.dmn.healthassistant.ui.information.article.ArticleAdapterActivity;
import com.dmn.healthassistant.ui.sport.SportFragment;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;

public class BottomFragment extends Fragment {
    private MaterialButton information, sport, individuality, diet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_nav, container, false);
        information = view.findViewById(R.id.information);
        sport = view.findViewById(R.id.sport);
        diet = view.findViewById(R.id.diet);
        individuality = view.findViewById(R.id.individuality);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        replaceFragment(new InformationFragment());
        changeColor(information, R.drawable.information1);

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new InformationFragment());
                changeColor(information, R.drawable.information1);
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SportFragment());
                changeColor(sport, R.drawable.sport1);
            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new DietFragment());
                changeColor(diet, R.drawable.diet1);
            }
        });
        individuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LogUtil.judgeLogin(getActivity())) {
                    replaceFragment(new IndividualityLoginFragment());
                } else {
                    replaceFragment(new IndividualityUnloginFragment());
                }
                changeColor(individuality, R.drawable.mine1);
            }
        });
    }
    public void changeColor(MaterialButton clickedButton, int clickedImage) {
        // 将所有按钮恢复到默认状态
        information.setTextColor(Color.BLACK);
        information.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(requireContext(), R.drawable.information), null ,null);
        sport.setTextColor(Color.BLACK);
        sport.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(requireContext(), R.drawable.sport), null ,null);
        diet.setTextColor(Color.BLACK);
        diet.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(requireContext(), R.drawable.diet), null ,null);
        individuality.setTextColor(Color.BLACK);
        individuality.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(requireContext(), R.drawable.mine), null ,null);

        // 更改被点击按钮的文本颜色和图片
        clickedButton.setTextColor(Color.parseColor("#0cb6ce"));
        clickedButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null, ContextCompat.getDrawable(requireContext(), clickedImage), null ,null);
    }
    private void replaceFragment(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.view, fragment);
            fragmentTransaction.commit();
        } else {
            System.out.println("失败");
        }
    }
}
