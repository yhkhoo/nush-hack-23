package com.example.nushhack23.model;

import javafx.beans.property.SimpleStringProperty;

public class BookedTimeslot {
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleStringProperty studentId;
    private SimpleStringProperty studentName;
    public BookedTimeslot(String start, String end, String studentId, String studentName){
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.studentId = new SimpleStringProperty(studentId);
        this.studentName = new SimpleStringProperty(studentName);
    }

    public String getStart() {
        return start.get();
    }

    public String getEnd() {
        return end.get();
    }

    public String getStudentId() {
        return studentId.get();
    }

    public String getStudentName() {
        return studentName.get();
    }
}
