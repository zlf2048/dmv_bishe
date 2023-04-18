package com.dmn.healthassistant.ui.individuality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.personal.BasicInformationActivity;
import com.dmn.healthassistant.ui.individuality.personal.HealthyInformationActivity;
import com.dmn.healthassistant.ui.individuality.setting.SettingActivity;
import com.google.android.material.button.MaterialButton;
//import com.dmn.healthassistant.ui.individuality.settings.SettingActivity;
//import com.dmn.healthassistant.ui.individuality.mycollection.MyCollectionActivity;
//import com.dmn.healthassistant.ui.individuality.myreply.ReplyActivity;
//import com.dmn.healthassistant.ui.individuality.mytopic.MyTopicActivity;

public class IndividualityBottomFragment extends Fragment {

    private MaterialButton settings;
    private MaterialButton healthy_information;
    private MaterialButton basic_information;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individuality_bottom, container, false);
        settings = view.findViewById(R.id.settings);
        healthy_information = view.findViewById(R.id.healthy_information);
        basic_information = view.findViewById(R.id.basic_information);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    }
}