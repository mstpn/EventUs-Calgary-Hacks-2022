package com.example.listtime;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadWrite {

    private static final String TAG = "ReadAndWriteSnippets";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

//    public readwrite(DatabaseReference database) {
    public ReadWrite() {
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]
    }

    public void writeEvent(Event event){
        DatabaseReference path = mDatabase.child("events").child("EventList").child(event.events);
        path.setValue(event);
        addEventListener(path);
//        mDatabase.child("events").child("EventList").child(event.events).setValue(event);
//        addEventListener(mDatabase.child("events").child("EventList").child(event.events));
    }

    private void addEventListener(DatabaseReference mEventReference) {
        // [START post_value_event_listener]
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Event event = dataSnapshot.getValue(Event.class);
                // ..
                if (event != null)
                    System.out.println(event.events);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mEventReference.addValueEventListener(eventListener);
        // [END post_value_event_listener]
    }

    public void removeEvent(String eventName){
        mDatabase.child("events").child("EventList").child(eventName).setValue(null);
    }

    // [START rtdb_write_new_user]
    public User writeNewUser(String userID, String name, String email) {
        com.example.listtime.User user = new com.example.listtime.User(userID, name, email);

        mDatabase.child("users").child(userID).setValue(user);
        addUserEventListener(mDatabase.child("users").child(userID), userID);
        return user;
    }

    private void addUserEventListener(DatabaseReference mUserReference, String userID) {
        // [START post_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // ..
                if (user != null)
                    System.out.println(user.username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mUserReference.addValueEventListener(userListener);
        // [END post_value_event_listener]
    }

    public void updateUser(String userID, User user){
        updateUser(userID, user.username, user.email);
    }

    public void updateUser(String userID, String name, String email){
        String key = mDatabase.child("users").child(userID).getKey();
        User user = new User(userID, name, email);
        Map<String, Object> userValues = user.toMap();

        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("/users/" + key, userValues);

        mDatabase.updateChildren(userUpdates);
    }

}
