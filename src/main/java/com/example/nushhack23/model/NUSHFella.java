package com.example.nushhack23.model;

import java.util.ArrayList;

public abstract class NUSHFella {
    private String id;
    private String name;
    private double hours;
    private double stars;
    private ArrayList<Rating> ratings;
    public NUSHFella(String id, String name, double hours, double stars) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.stars = stars;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getHours() {
        return hours;
    }

    public double getStars() {
        return stars;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    // Methods
    public void calculateStars(Rating newRating){
        ratings.add(newRating);
        double totalRatingsSum = 0;
        for(Rating i : ratings){
            totalRatingsSum += i.getStars();
        }
        setStars((totalRatingsSum + newRating.stars) / ratings.size());
    }

    public void addHours(double newHours){
        setHours(getHours() + newHours);
    }
}
