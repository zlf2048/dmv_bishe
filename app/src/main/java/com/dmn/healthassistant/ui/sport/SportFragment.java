package com.dmn.healthassistant.ui.sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.ui.sport.FitnessAchievement.FitnessAchievementActivity;
import com.google.android.material.card.MaterialCardView;

public class SportFragment extends Fragment {

    private MaterialCardView FitnessAchievementMaterialCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        FitnessAchievementMaterialCardView = view.findViewById(R.id.FitnessAchievementMaterialCardView);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FitnessAchievementMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FitnessAchievementActivity.class);
                startActivity(intent);
            }
        });
    }
}