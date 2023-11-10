package com.example.nushhack23.model;

public abstract class NUSHFella {
    private String id;
    private String name;
    private double hours;
    private double rating;
    public NUSHFella(String id, String name, double hours, double rating) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.rating = rating;
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

    public double getRating() {
        return rating;
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

    public void setRating(double rating) {
        this.rating = rating;
    }
}
