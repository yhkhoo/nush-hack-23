package com.example.nushhack23.model;

import java.util.ArrayList;

public class Teacher extends NUSHFella {

    private ArrayList<Timeslot> availableTimeslots;
    private ArrayList<Timeslot> bookedTimeslots;

    public Teacher(String student_ID, String name, String password, double hours, double stars, ArrayList<Rating> ratings, ArrayList<String> subjects, ArrayList<Timeslot> availableTimeslots, ArrayList<Timeslot> bookedTimeslots){
        super(student_ID, name, password, hours, stars, ratings, subjects);
        this.availableTimeslots = availableTimeslots;
        this.bookedTimeslots = bookedTimeslots;
    }

    public Teacher(String student_ID, String name, String password, double hours, double stars){
        this(student_ID, name, password, hours, stars, new ArrayList<Rating>(), new ArrayList<String>(), new ArrayList<Timeslot>(), new ArrayList<Timeslot>());
    }

    public Teacher(String student_ID, String name, String password)
    {
        this(student_ID, name, password, 0.0, 0.0);
    }

    public void setAvailableTimeslots(ArrayList<Timeslot> availableTimeslots) {
        this.availableTimeslots = availableTimeslots;
    }

    public void setBookedTimeslots(ArrayList<Timeslot> bookedTimeslots) {
        this.bookedTimeslots = bookedTimeslots;
    }

    public ArrayList<Timeslot> getAvailableTimeslots(){
        return this.availableTimeslots;
    }

    public ArrayList<Timeslot> getBookedTimeslots(){
        return this.bookedTimeslots;
    }

    public void bookTimeslot(Timeslot t1){
        if(availableTimeslots.contains(t1))
        {
            bookedTimeslots.add(t1);
            availableTimeslots.remove(t1);
        }
    }






}
