package com.example.nushhack23.model;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Timeslot {
    private LocalDateTime start;
    private LocalDateTime end;
    private long duration;
    private SimpleStringProperty startS;
    private SimpleStringProperty endS;
    private SimpleStringProperty durationS;

    public Timeslot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
        this.duration = start.until(end, ChronoUnit.MINUTES);
        this.startS = new SimpleStringProperty(start.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        this.endS = new SimpleStringProperty(end.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        this.durationS = new SimpleStringProperty(String.format("%d minutes", duration));
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getStartS() {
        return startS.get();
    }

    public String getEndS() {
        return endS.get();
    }

    public String getDurationS() {
        return durationS.get();
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(start.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        sb.append(" to ");
        if(start.toLocalDate().equals(end.toLocalDate())) {
            sb.append(end.format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            sb.append(end.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        }
        return sb.toString();
    }
}
