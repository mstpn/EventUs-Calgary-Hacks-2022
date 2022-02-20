package com.example.listtime;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.ArrayList;

// [START rtdb_user_class]
@IgnoreExtraProperties
public class Event {

    public String events, lastMessage, distance, phoneNo, country;
    public ArrayList<User> attendees;
    public User owner;

    public Event(){
        this.events = "default";
        this.lastMessage = "hi";
        this.distance = "4";
        this.phoneNo = "123";
        this.country = "CAN";
        this.attendees = new ArrayList<User>();
    }

    public Event(String name, String lastMessage, String lastMsgTime, String phoneNo, String country, User theOwner) {
        this.events = name;
        this.lastMessage = lastMessage;
        this.distance = lastMsgTime;
        this.phoneNo = phoneNo;
        this.country = country;
        this.owner = theOwner;
        this.attendees = new ArrayList<User>();
        if(theOwner != null) {
            this.attendees.add(theOwner);
        }
    }

    public void addAttendee(User user){
        this.attendees.add(user);
    }

    public void removeAttendee(User user){
        this.attendees.remove(user);
    }

    @Override
    public String toString() {
        return "Event{" +
                "events='" + events + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", distance='" + distance + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", country='" + country + '\'' +
                ", attendees=" + attendees +
                ", owner=" + owner +
                '}';
    }
}

