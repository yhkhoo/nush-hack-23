package com.example.nushhack23.model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Teacher extends NUSHFella {

    private ArrayList<LocalDateTime> timeslots;

    public Teacher(String student_ID, String name, double hours, double rating){
        super(student_ID, name, hours, rating);
    }







}
