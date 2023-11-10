package com.example.nushhack23.model;

import java.util.ArrayList;

public class Teacher extends NUSHFella {

    private ArrayList<Timeslot> availableTimeslots;
    private ArrayList<Timeslot> bookedTimeslots;

    public Teacher(String student_ID, String name, String password, double hours, double rating){
        super(student_ID, name, password, hours, rating);
    }

    public void bookTimeslot(Timeslot t1){
        if(availableTimeslots.contains(t1))
        {
            bookedTimeslots.add(t1);
            availableTimeslots.remove(t1);
        }
    }






}
