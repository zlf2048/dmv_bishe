package com.dmn.healthassistant.ui.diet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.diet.menu.CarbActivity;
import com.dmn.healthassistant.ui.diet.menu.EyeActivity;
import com.dmn.healthassistant.ui.diet.menu.FeActivity;
import com.dmn.healthassistant.ui.diet.menu.OldActivity;
import com.dmn.healthassistant.ui.diet.menu.PregnancyActivity;
import com.dmn.healthassistant.ui.diet.menu.SleepActivity;
import com.dmn.healthassistant.ui.sport.FitnessAchievement.FitnessAchievementActivity;
import com.google.android.material.card.MaterialCardView;

public class DietFragment extends Fragment {

    private MaterialCardView menu1;
    private MaterialCardView menu2;
    private MaterialCardView menu3;
    private MaterialCardView menu4;
    private MaterialCardView menu5;
    private MaterialCardView menu6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        menu1 = view.findViewById(R.id.menu1);
        menu2 = view.findViewById(R.id.menu2);
        menu3 = view.findViewById(R.id.menu3);
        menu4 = view.findViewById(R.id.menu4);
        menu5 = view.findViewById(R.id.menu5);
        menu6 = view.findViewById(R.id.menu6);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OldActivity.class);
                startActivity(intent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PregnancyActivity.class);
                startActivity(intent);
            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SleepActivity.class);
                startActivity(intent);
            }
        });

        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarbActivity.class);
                startActivity(intent);
            }
        });

        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EyeActivity.class);
                startActivity(intent);
            }
        });

        menu6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeActivity.class);
                startActivity(intent);
            }
        });
    }
}