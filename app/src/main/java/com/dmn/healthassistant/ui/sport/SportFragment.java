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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SportFragment extends Fragment {

    private MaterialCardView FitnessAchievementMaterialCardView;
    TextView step, minute, length, time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        FitnessAchievementMaterialCardView = view.findViewById(R.id.FitnessAchievementMaterialCardView);
        step = view.findViewById(R.id.step);
        minute = view.findViewById(R.id.minute);
        length = view.findViewById(R.id.length);
        time = view.findViewById(R.id.time);

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
                int steps = sharedPreferences.getInt("steps", 0);
                step.setText(String.valueOf(steps));

                double secondsPerStep = 0.5; // 假设每步需要0.5秒
                double totalTimeInSeconds = steps * secondsPerStep; // 计算总时间（秒）
                int minutes = (int) Math.round(totalTimeInSeconds / 60); // 计算分钟数并四舍五入
                minute.setText(String.valueOf(minutes));

                double stepLengthInMeters = 0.7; // 假设每步的步长为0.7米
                double distanceInKilometers = steps * stepLengthInMeters / 1000; // 计算行走距离（公里）
                String distanceString = String.format("%.2f", distanceInKilometers);
                length.setText("健走"+distanceString+"公里");

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);
                String timeString = dateFormat.format(calendar.getTime());
                time.setText(timeString);

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