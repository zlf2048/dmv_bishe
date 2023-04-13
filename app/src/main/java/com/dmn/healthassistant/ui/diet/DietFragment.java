package com.dmn.healthassistant.ui.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class DietFragment extends Fragment {

//    ActivityMainBinding binding;
//    ListAdapter listAdapter;
//    ArrayList<ListData> dataArrayList = new ArrayList<>();
//    ListData listData;
//
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        int[] imageList = {R.drawable.pasta,R.drawable.maggi,R.drawable.cake,R.drawable.pancake,R.drawable.pizza,R.drawable.burger,R.drawable.fries};
//        int[] ingredientList = {R.string.pastaIngredients,R.string.maggiIngredients,R.string.cakeIngredients,R.string.pancakeIngredients,R.string.pizzaIngredients,R.string.burgerIngredients,R.string.friesIngredients};
//        int[]
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        return view;
    }
}
