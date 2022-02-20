package com.example.listtime;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {

    public String name;
    public String username;
    public String email;
    //Needs to be Map to update correctly, ran out of time
    public ArrayList<Event> attending;

    public User() {
        this.name = "John";
        this.username = "Olong Johnson";
        this.email = "thisisreal@realmail.com";
        this.attending = new ArrayList<Event>();
    }

    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.attending = new ArrayList<Event>();
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("email", email);

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void createEvent(String name, String lastMessage, String lastMsgTime, String phoneNo, String country, User owner){
        ReadWrite rw = new ReadWrite();

        Event event = new Event(name, lastMessage, lastMsgTime, phoneNo, country, owner);
        rw.writeEvent(event);
        this.attending.add(event);
        rw.updateUser(this.username, this.name, this.email);
    }

}
