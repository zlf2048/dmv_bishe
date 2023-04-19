package com.dmn.healthassistant.ui.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.ui.information.article.ArticleAdapterActivity;
import com.dmn.healthassistant.ui.information.journal.JournalAdapterActivity;
import com.dmn.healthassistant.ui.sport.FitnessAchievement.FitnessAchievementActivity;
import com.google.android.material.card.MaterialCardView;

public class InformationFragment extends Fragment {

    private MaterialCardView article;
    private MaterialCardView journal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        article = view.findViewById(R.id.article);
        journal = view.findViewById(R.id.journal);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArticleAdapterActivity.class);
                startActivity(intent);
            }
        });
        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JournalAdapterActivity.class);
                startActivity(intent);
            }
        });
    }
}