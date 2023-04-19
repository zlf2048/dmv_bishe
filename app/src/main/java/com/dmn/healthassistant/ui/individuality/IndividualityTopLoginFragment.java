package com.dmn.healthassistant.ui.individuality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.individuality.login.LoginActivity;
import com.dmn.healthassistant.util.LoginInfo;

public class IndividualityTopLoginFragment extends Fragment {
    TextView textView, id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individuality_top_login, container, false);
        textView = view.findViewById(R.id.userName);
        id = view.findViewById(R.id.id);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginInfo loginInfo = new LoginInfo(getActivity());
        String nicknameText = loginInfo.getLoginInfo().getNickname();
        String idText = loginInfo.getLoginInfo().getId();
        if (nicknameText == null || nicknameText.isEmpty()) {
            textView.setText("取个好听的名字吧！");
        } else {
            textView.setText(nicknameText);
        }
        id.setText(idText);
//        topUnloginLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
