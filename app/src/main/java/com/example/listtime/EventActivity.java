package com.example.listtime;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listtime.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

    ActivityEventBinding binding;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if(intent != null){
            String events = intent.getStringExtra("Event"); //TODO
            String description = intent.getStringExtra("Description");
            String location = intent.getStringExtra("Location");

            binding.eventProfile.setText(events);
            binding.descriptionProfile.setText(description);
            binding.locationProfile.setText(location);


        }
    }
}
