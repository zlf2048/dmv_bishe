package com.dmn.healthassistant.ui.sport;

import static com.dmn.healthassistant.HealthAssistantApplication.context;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.ui.sport.FitnessAchievement.FitnessAchievementActivity;
import com.google.android.material.card.MaterialCardView;

public class SportFragment extends Fragment {

    private MaterialCardView FitnessAchievementMaterialCardView;
    TextView step;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        FitnessAchievementMaterialCardView = view.findViewById(R.id.FitnessAchievementMaterialCardView);
        step = view.findViewById(R.id.step);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = context.getSharedPreferences("step_data", Context.MODE_PRIVATE);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                step.setText(String.valueOf(sharedPreferences.getInt("steps", 0)));
                handler.postDelayed(this, 1000);
            }
        });

        FitnessAchievementMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessAchievementActivity.class);
                startActivity(intent);
            }
        });
    }
}