package com.example.nushhack23.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timeslot {
    private LocalDateTime start;
    private LocalDateTime end;

    public Timeslot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
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
            sb.append(start.format(DateTimeFormatter.ofPattern("HH:mm")));
        } else {
            sb.append(start.format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        }
        return sb.toString();
    }
}
