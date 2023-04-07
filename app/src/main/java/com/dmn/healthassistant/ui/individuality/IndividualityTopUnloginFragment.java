package com.dmn.healthassistant.ui.individuality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;

public class IndividualityTopUnloginFragment extends Fragment {
    private ConstraintLayout topUnloginLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individuality_top_unlogin, container, false);
        topUnloginLayout = view.findViewById(R.id.topUnloginLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topUnloginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

//    ConstraintLayout constraintLayout = activity.findViewById(R.id.topUnloginLayout);
//if (constraintLayout != null) {
//        Toast.makeText(activity, "不为空", Toast.LENGTH_SHORT).show();
//        constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(activity, "准备登陆", Toast.LENGTH_SHORT).show();
//            }
//        });
//    } else {
//        Toast.makeText(activity, "为空", Toast.LENGTH_SHORT).show();
//    }
}