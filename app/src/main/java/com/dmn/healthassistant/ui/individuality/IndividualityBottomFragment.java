package com.dmn.healthassistant.ui.individuality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.collection.CollectionActivity;
import com.dmn.healthassistant.ui.individuality.personal.BasicInformationActivity;
import com.dmn.healthassistant.ui.individuality.personal.HealthyInformationActivity;
import com.dmn.healthassistant.ui.individuality.setting.SettingActivity;
import com.dmn.healthassistant.util.LogUtil;
import com.google.android.material.button.MaterialButton;

public class IndividualityBottomFragment extends Fragment {

    private MaterialButton settings;
    private MaterialButton healthy_information;
    private MaterialButton basic_information;
    private MaterialButton collection;

    private ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individuality_bottom, container, false);
        settings = view.findViewById(R.id.settings);
        collection = view.findViewById(R.id.collection);
        healthy_information = view.findViewById(R.id.healthy_information);
        basic_information = view.findViewById(R.id.basic_information);
        constraintLayout = view.findViewById(R.id.bottomView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!LogUtil.judgeLogin(getActivity())) {
            constraintLayout.setVisibility(View.INVISIBLE);
        }
//        collection.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyCollectionActivity.class);
//                startActivity(intent);
//            }
//        });

        basic_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BasicInformationActivity.class);
                startActivity(intent);
            }
        });

        healthy_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthyInformationActivity.class);
                startActivity(intent);
            }
        });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });
    }
}