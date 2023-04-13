package com.dmn.healthassistant.ui.individuality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.setting.SettingActivity;
//import com.dmn.healthassistant.ui.individuality.settings.SettingActivity;
//import com.dmn.healthassistant.ui.individuality.mycollection.MyCollectionActivity;
//import com.dmn.healthassistant.ui.individuality.myreply.ReplyActivity;
//import com.dmn.healthassistant.ui.individuality.mytopic.MyTopicActivity;

public class IndividualityBottomFragment extends Fragment {

    private ConstraintLayout settings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individuality_bottom, container, false);
        settings = view.findViewById(R.id.settings);
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
//        topic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MyTopicActivity.class);
//                startActivity(intent);
//            }
//        });
//        reply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ReplyActivity.class);
//                startActivity(intent);
//            }
//        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}