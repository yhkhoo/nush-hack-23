package com.example.nushhack23.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;


public class Database {
    private ArrayList<Student> studentDB;
    private ArrayList<Teacher> teacherDB;
    private String studentFile, teacherFile;
    Database(String studentFile, String teacherFile)
    {
        this.studentDB = new ArrayList<>();
        this.teacherDB = new ArrayList<>();
    }

    public ArrayList<Student> getStudentDB() {
        return studentDB;
    }
    public ArrayList<Teacher> getTeacherDB() {
        return teacherDB;
    }

    public void loadStudentDB(String filename)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(",");
                String studentID = tokens[0];
                String name = tokens[1];
                String password = tokens[2];
                double hours = Double.parseDouble(tokens[3]);
                double stars = Double.parseDouble(tokens[4]);

                ArrayList<Rating> ratings = new ArrayList<Rating>();
                ArrayList<String> subjects = new ArrayList<String>();
                ArrayList<Timeslot> timeslotsBooked = new ArrayList<Timeslot>();

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i += 2)
                {
                    ratings.add(new Rating(Double.parseDouble(tokens[i]), tokens[i+1]));
                }

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i++)
                {
                    subjects.add(tokens[i]);
                }

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i += 2)
                {
                    timeslotsBooked.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i+1]), 0, ZoneOffset.ofHours(8))));
                }

                studentDB.add(new Student(studentID, name, password, hours, stars, ratings, subjects, timeslotsBooked));
            }
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }

    public void loadTeacherDB(String filename)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(",");

                String studentID = tokens[0];
                String name = tokens[1];
                String password = tokens[2];
                double hours = Double.parseDouble(tokens[3]);
                double stars = Double.parseDouble(tokens[4]);


                ArrayList<Rating> ratings = new ArrayList<Rating>();
                ArrayList<String> subjects = new ArrayList<String>();
                ArrayList<Timeslot> availableTimeslots = new ArrayList<Timeslot>();
                ArrayList<Timeslot> bookedTimeslots = new ArrayList<Timeslot>();

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i += 2)
                {
                    ratings.add(new Rating(Double.parseDouble(tokens[i]), tokens[i+1]));
                }

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i++)
                {
                    subjects.add(tokens[i]);
                }

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i += 2)
                {
                    availableTimeslots.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i+1]), 0, ZoneOffset.ofHours(8))));
                }

                line = reader.readLine();
                tokens = line.split(",");
                for(int i = 0; i < tokens.length; i += 2)
                {
                    bookedTimeslots.add(new Timeslot(LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i]), 0, ZoneOffset.ofHours(8)), LocalDateTime.ofEpochSecond(Long.parseLong(tokens[i+1]), 0, ZoneOffset.ofHours(8))));
                }


                teacherDB.add(new Teacher(studentID, name, password, hours, stars, ratings, subjects, availableTimeslots, bookedTimeslots));
            }
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }



}

