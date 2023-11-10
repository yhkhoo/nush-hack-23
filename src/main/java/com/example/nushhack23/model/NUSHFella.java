package com.example.nushhack23.model;

import java.util.ArrayList;

public abstract class NUSHFella {
    private String id;
    private String name;
    private String password;
    private double hours;
    private double stars;
    private String description;
    private String subject;
    private ArrayList<Rating> ratings;
    public NUSHFella(String id, String name, String password, double hours, double stars) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hours = hours;
        this.stars = stars;
    }

    // Accessors
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getPassword() { return password; }
    public double getHours() {
        return hours;
    }

    public double getStars() {
        return stars;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    // Mutators
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        if (!NUSHFella.validPassword(password))
            throw new IllegalArgumentException("Invalid user password!");
        this.password = password;
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

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    // Methods

    public static boolean validPassword(String pwd) {
        boolean hasDigit = false;
        boolean hasSymbol = false;
        boolean hasUpper = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSymbol = true;
            if (Character.isUpperCase(c)) hasUpper = true;
        }

        return 8 <= pwd.length() && pwd.length() <= 16 && hasDigit && hasSymbol && hasUpper;
    }
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
