package com.dmn.healthassistant.ui.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dmn.healthassistant.R;

import java.util.ArrayList;

public class DietFragment extends Fragment {
//    FragmentDietBinding binding;
//    ListAdapter listAdapter;
//    ArrayList<ListData> dataArrayList = new ArrayList<>();
//    ListData listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        return view;
    }
}
