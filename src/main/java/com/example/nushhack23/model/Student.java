package com.example.nushhack23.model;

import java.util.ArrayList;

public class Student extends NUSHFella {

    private ArrayList<Timeslot> timeslotsBooked;
    public Student(String id, String name, String password, double hours, double stars, ArrayList<Rating> ratings, ArrayList<String> subjects, ArrayList<Timeslot> timeslotsBooked) {
        super(id, name, password, hours, stars, ratings, subjects);
        this.timeslotsBooked = timeslotsBooked;
    }
}
