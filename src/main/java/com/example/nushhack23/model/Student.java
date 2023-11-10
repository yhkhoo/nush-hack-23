package com.example.nushhack23.model;

import java.util.ArrayList;

public class Student extends NUSHFella {

    private ArrayList<Timeslot> timeslotsBooked;
    private ArrayList<String> bookedTeachers;
    public Student(String id, String name, String password, double hours, double stars, ArrayList<Rating> ratings, ArrayList<String> subjects, ArrayList<Timeslot> timeslotsBooked, ArrayList<String> bookedTeachers) {
        super(id, name, password, hours, stars, ratings, subjects);
        this.timeslotsBooked = timeslotsBooked;
        this.bookedTeachers = bookedTeachers;
    }

    public Student(NUSHFella nushFella, ArrayList<Timeslot> timeslotsBooked, ArrayList<String> bookedTeachers) {
        super(nushFella);
        this.timeslotsBooked = timeslotsBooked;
        this.bookedTeachers = bookedTeachers;
    }

    public ArrayList<Timeslot> getTimeslotsBooked() {
        return timeslotsBooked;
    }

    public ArrayList<String> getBookedTeachers() {
        return bookedTeachers;
    }

    public void setTimeslotsBooked(ArrayList<Timeslot> timeslotsBooked) {
        this.timeslotsBooked = timeslotsBooked;
    }

    public void setBookedTeachers(ArrayList<String> bookedTeachers) {
        this.bookedTeachers = bookedTeachers;
    }

    public Student(String id, String name, String password)
    {
        this(id, name, password, 0.0, 0.0, new ArrayList<Rating>(), new ArrayList<String>(), new ArrayList<Timeslot>(), new ArrayList<String>());
    }

    public void bookTimeslot(Teacher tutor, Timeslot t1)
    {
        tutor.getAvailableTimeslots().remove(t1);
        tutor.getBookedTimeslots().add(t1);
    }

    public void leaveRating(Teacher tutor, Rating rating)
    {
        tutor.calculateStars(rating);
        tutor.getRatings().add(rating);
    }


}
