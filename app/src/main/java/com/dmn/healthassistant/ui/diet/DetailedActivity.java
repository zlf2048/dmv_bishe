package com.dmn.healthassistant.ui.diet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dmn.healthassistant.R;
import com.dmn.healthassistant.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if(intent != null) {
            String name = intent.getStringExtra("name");
            String time = intent.getStringExtra("time");
            int ingredients = intent.getIntExtra("ingredients", R.string.maggiIngredients);
            int desc = intent.getIntExtra("desc", R.string.maggieDesc);
            int image = intent.getIntExtra("image", R.drawable.maggi);

            binding.detailName.setText(name);
            binding.detailDesc.setText(desc);
            binding.detailImage.setImageResource(image);
        }
    }
}