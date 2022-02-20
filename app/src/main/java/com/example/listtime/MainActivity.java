package com.example.listtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.listtime.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] events = {"Street Hockey","DnD","Soccer","Running Meetup","Biking","Hiking","Bouldering","Cosplay Contest","Dogs and Coffee"};
        String[] lastMessage = {"Come play","Fun","Bring your own ball","Any level","Bring water","Bring harness","Anyone welcome","Come join!","woof"};
        String[] distance = {"2 km","2.5 km","3.3 km","5.2 km","7 km","10.8 km","14 km","25 km","30 km"};
        String[] phoneNo = {"1234567","1234567","1234567","1234567","1234567","1234567","1234567","1234567","1234567"};
        String[] country = {"Canada","Canada","Canada","Canada","Canada","Canada","Canada","Canada","Canada"};



        //FOR TESTING
        ReadWrite rw = new ReadWrite();

        ArrayList<Event> eventArrayList = new ArrayList<>();

        for (int i = 0; i <9; i++){
            Event event = new Event(events[i],lastMessage[i],distance[i],phoneNo[i],country[i], null);
            eventArrayList.add(event);
            if (event != null)
                rw.writeEvent(event);
        }

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();;
        User panos = rw.writeNewUser("Panos","Panos the Wonder Child","funky@jam.com");
        ArrayList<User> users = new ArrayList<>();
        users.add(panos);



        ListAdapter listAdapter = new ListAdapter(MainActivity.this,eventArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, EventActivity.class);
                i.putExtra("Event", events[position]);
                i.putExtra("Location", country[position]);
                i.putExtra("Description", phoneNo[position]);
                startActivity(i);
            }
        });

        Button addBtn = (Button) findViewById(R.id.btnProfile);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.view_user);
            }
        });

        }
}