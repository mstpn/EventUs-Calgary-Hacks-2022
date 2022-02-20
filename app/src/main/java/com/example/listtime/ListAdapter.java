package com.example.listtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Event> {

    public ListAdapter (Context context, ArrayList<Event> eventArrayList){
        super(context,R.layout.list_item,eventArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        TextView events = convertView.findViewById(R.id.personName);
        TextView location = convertView.findViewById(R.id.location);
        TextView distance = convertView.findViewById(R.id.distance);

        events.setText(event.events);
        location.setText(event.lastMessage);
        distance.setText(event.distance);


        return convertView;
    }
}
