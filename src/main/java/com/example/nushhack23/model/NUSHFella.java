package com.example.nushhack23.model;

import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NUSHFella {
    private String id;
    private String name;
    private String password;
    private double hours;
    private double stars;
    private String description;
    private ArrayList<String> subjects;
    private ArrayList<Rating> ratings;
    private SimpleStringProperty idProperty;
    private SimpleStringProperty nameProperty;
    private SimpleStringProperty starsProperty;
    public NUSHFella(String id, String name, String password, double hours, double stars, ArrayList<Rating> ratings, ArrayList<String> subjects) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hours = hours;
        this.stars = stars;
        this.ratings = ratings;
        this.subjects = subjects;
        idProperty = new SimpleStringProperty(id);
        nameProperty = new SimpleStringProperty(name);
        starsProperty = new SimpleStringProperty(String.format("%1.2f", stars));
    }

    public NUSHFella(NUSHFella otherFella) {
        this(otherFella.getId(), otherFella.getName(), otherFella.getPassword(), otherFella.getHours(), otherFella.getStars(), otherFella.getRatings(), otherFella.getSubjects());
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

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public String getSubjectsString() {
        StringBuilder sb = new StringBuilder();
        for(String i: subjects){
            sb.append(i);
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        return sb.toString();
    }

    // Mutators
    public void setId(String id) {
        this.id = id;
        idProperty.set(id);
    }

    public void setPassword(String password) {
        if (!NUSHFella.validPassword(password))
            throw new IllegalArgumentException("Invalid user password!");
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
        nameProperty.set(name);
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setStars(double stars) {
        this.stars = stars;
        starsProperty.set(String.format("%1.2f", stars));
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setSubject(ArrayList<String> subject)
    {
        this.subjects = subject;
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
