package com.example.nushhack23.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;


public class Database {
    private ArrayList<Student> studentDB;
    private ArrayList<Teacher> teacherDB;

    public Database() {
        this.studentDB = new ArrayList<>();
        this.teacherDB = new ArrayList<>();
    }

    public ArrayList<Student> getStudentDB() {
        return studentDB;
    }

    public ArrayList<Teacher> getTeacherDB() {
        return teacherDB;
    }

    public Student getStudent(String id) {
        for(Student i: studentDB) {
            if(i.getId().equals(id))
                return i;
        }
        return null;
    }

    public Teacher getTeacher(String id) {
        for(Teacher i: teacherDB) {
            if(i.getId().equals(id))
                return i;
        }
        return null;
    }

    public NUSHFella readNUSHFella(String line1, String line2, String line3) {
        String[] tokens = line1.split("\\|");
        String studentID = tokens[0];
        String name = tokens[1];
        String password = tokens[2];
        double hours = Double.parseDouble(tokens[3]);
        double stars = Double.parseDouble(tokens[4]);

        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> subjects = new ArrayList<String>();

        tokens = line2.split("\\|");
        for (int i = 0; i < tokens.length; i += 2) {
            ratings.add(new Rating(Double.parseDouble(tokens[i]), tokens[i + 1]));
        }

        tokens = line3.split("\\|");
        for (int i = 0; i < tokens.length; i++) {
            subjects.add(tokens[i]);
        }
        return new NUSHFella(studentID, name, password, hours, stars, ratings, subjects);
    }

    public String writeNUSHFella(NUSHFella fella) {
        StringBuilder sb = new StringBuilder();
        sb.append(fella.getId());
        sb.append('|');
        sb.append(fella.getName());
        sb.append('|');
        sb.append(fella.getPassword());
        sb.append('|');
        sb.append(fella.getHours());
        sb.append('|');
        sb.append(fella.getStars());
        sb.append('\n');

        for(Rating rating: fella.getRatings()) {
            sb.append(rating.getStars());
            sb.append('|');
            sb.append(rating.getComment());
            sb.append('|');
        }
        if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
        sb.append('\n');

        for(String subject: fella.getSubjects()) {
            sb.append(subject);
            sb.append('|');
        }
        if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
        sb.append('\n');
        return sb.toString();
    }

    public void loadStudentDB(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens;
                NUSHFella fella = new NUSHFella(readNUSHFella(line, reader.readLine(), reader.readLine()));

                ArrayList<Timeslot> timeslotsBooked = new ArrayList<Timeslot>();
                ArrayList<String> bookedTeachers = new ArrayList<String>();

                line = reader.readLine();
                tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i += 2) {
                    timeslotsBooked.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i + 1]), 0, ZoneOffset.ofHours(8))));
                }

                line = reader.readLine();
                tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i += 1) {
                    bookedTeachers.add(tokens[i]);
                }

                studentDB.add(new Student(fella, timeslotsBooked, bookedTeachers));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTeacherDB(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens;
                NUSHFella fella = new NUSHFella(readNUSHFella(line, reader.readLine(), reader.readLine()));

                ArrayList<Timeslot> availableTimeslots = new ArrayList<Timeslot>();
                ArrayList<Timeslot> bookedTimeslots = new ArrayList<Timeslot>();
                ArrayList<String> bookers = new ArrayList<>();

                line = reader.readLine();
                if(!line.isEmpty()) {
                    tokens = line.split("\\|");
                    for (int i = 0; i < tokens.length; i += 2) {
                        availableTimeslots.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i + 1]), 0, ZoneOffset.ofHours(8))));
                    }
                }

                line = reader.readLine();
                if(!line.isEmpty()) {
                    tokens = line.split("\\|");
                    for (int i = 0; i < tokens.length; i += 2) {
                        bookedTimeslots.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i + 1]), 0, ZoneOffset.ofHours(8))));
                    }
                }

                line = reader.readLine();
                if(!line.isEmpty()) {
                    tokens = line.split("\\|");
                    for (int i = 0; i < tokens.length; i += 1) {
                        bookers.add(tokens[i]);
                    }
                }

                ArrayList<BookedTimeslot> bookedTimeslots1 = new ArrayList<BookedTimeslot>();

                for(int i=0; i<bookedTimeslots.size(); i += 1) {
                    Timeslot t = bookedTimeslots.get(i);
                    String id = bookers.get(i);
                    bookedTimeslots1.add(new BookedTimeslot(t.getStart().toString(), t.getEnd().toString(), id, getStudent(id).getName()));
                }

                teacherDB.add(new Teacher(fella, availableTimeslots, bookedTimeslots1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeStudents(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for(Student student: studentDB) {
                sb.append(writeNUSHFella(student));
                for(Timeslot timeslot: student.getTimeslotsBooked()) {
                    sb.append(timeslot.getStart().atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                    sb.append(timeslot.getEnd().atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                }
                if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
                sb.append('\n');
                for(String teacher: student.getBookedTeachers()) {
                    sb.append(teacher);
                    sb.append('|');
                }
                if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
                sb.append('\n');
            }
            System.out.println(sb);
            writer.print(sb.toString());
            writer.close();
        } catch(Exception e) {
            e.printStackTrace(); // cry about it
        }
    }

    public void writeTeachers(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            StringBuilder sb = new StringBuilder();
            for(Teacher teacher: teacherDB) {
                sb.append(writeNUSHFella(teacher));
                for(Timeslot timeslot: teacher.getAvailableTimeslots()) {
                    sb.append(timeslot.getStart().atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                    sb.append(timeslot.getEnd().atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                }
                if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
                sb.append('\n');
                for(BookedTimeslot timeslot: teacher.getBookedTimeslots()) {
                    sb.append(LocalDateTime.parse(timeslot.getStart()).atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                    sb.append(LocalDateTime.parse(timeslot.getEnd()).atZone(ZoneId.of("Asia/Singapore")).toEpochSecond());
                    sb.append('|');
                }
                if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
                sb.append('\n');
                for(BookedTimeslot timeslot: teacher.getBookedTimeslots()) {
                    String student = timeslot.getStudentId();
                    sb.append(student);
                    sb.append('|');
                }
                if(sb.charAt(sb.length()-1) == '|')
                    sb.deleteCharAt(sb.length()-1);
                sb.append('\n');
            }
            writer.print(sb.toString());
            writer.close();
        } catch(Exception e) {
            e.printStackTrace(); // cry about it
        }
    }
}

