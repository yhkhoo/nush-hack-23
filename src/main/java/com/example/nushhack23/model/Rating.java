package com.example.nushhack23.model;

public class Rating {
    public double stars;
    public String comment;

    public Rating(double stars, String comment) {
        this.stars = stars;
        this.comment = comment;
    }

    public Rating(double stars) {
        this(stars, "");
    }

    public double getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
