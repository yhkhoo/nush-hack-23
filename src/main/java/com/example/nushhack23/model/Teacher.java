package com.example.nushhack23.model;

import java.util.ArrayList;

public class Teacher extends NUSHFella {

    private ArrayList<Timeslot> availableTimeslots;
    private ArrayList<BookedTimeslot> bookedTimeslots;

    public Teacher(String student_ID, String name, String password, double hours, double stars, ArrayList<Rating> ratings, ArrayList<String> subjects, ArrayList<Timeslot> availableTimeslots, ArrayList<BookedTimeslot> bookedTimeslots){
        super(student_ID, name, password, hours, stars, ratings, subjects);
        this.availableTimeslots = availableTimeslots;
        this.bookedTimeslots = bookedTimeslots;
    }

    public Teacher(NUSHFella nushFella, ArrayList<Timeslot> availableTimeslots, ArrayList<BookedTimeslot> bookedTimeslots) {
        super(nushFella);
        this.availableTimeslots = availableTimeslots;
        this.bookedTimeslots = bookedTimeslots;
    }

    public Teacher(String student_ID, String name, String password, double hours, double stars){
        this(student_ID, name, password, hours, stars, new ArrayList<Rating>(), new ArrayList<String>(), new ArrayList<Timeslot>(), new ArrayList<BookedTimeslot>());
    }

    public Teacher(String student_ID, String name, String password)
    {
        this(student_ID, name, password, 0.0, 0.0);
    }

    public void setAvailableTimeslots(ArrayList<Timeslot> availableTimeslots) {
        this.availableTimeslots = availableTimeslots;
    }

    public void setBookedTimeslots(ArrayList<BookedTimeslot> bookedTimeslots) {
        this.bookedTimeslots = bookedTimeslots;
    }

    public ArrayList<Timeslot> getAvailableTimeslots(){
        return this.availableTimeslots;
    }

    public ArrayList<BookedTimeslot> getBookedTimeslots(){
        return this.bookedTimeslots;
    }

    public void bookTimeslot(Timeslot t1, Student booker){
        if(availableTimeslots.contains(t1))
        {
            availableTimeslots.remove(t1);
            bookedTimeslots.add(new BookedTimeslot(t1.getStart().toString(), t1.getEnd().toString(), booker.getId(), booker.getName()));
        } else {
            System.out.println("timeslot not in available list!");
        }
    }

    public void leaveRemark(Student student, Rating rating)
    {
        student.calculateStars(rating);
        student.getRatings().add(rating);
    }




}
