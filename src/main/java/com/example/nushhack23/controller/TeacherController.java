package com.example.nushhack23.controller;

import com.example.nushhack23.model.Database;
import com.example.nushhack23.model.Statics;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    private Database db;
    @FXML
    private Button addTimeslotBtn;

    @FXML
    private Button editBtn;

    @FXML
    private ListView<?> listView;

    @FXML
    private Label myHours;

    @FXML
    private Label myId;

    @FXML
    private Label myName;

    @FXML
    private ImageView myPfp;

    @FXML
    private Label myStars;

    @FXML
    private Label mySubjects;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private Label studentHours;

    @FXML
    private Label studentID;

    @FXML
    private Label studentName;

    @FXML
    private ImageView studentPfp;

    @FXML
    private Label studentStars;

    @FXML
    private Label studentSubjects;

    @FXML
    private TextArea subjectTA;

    @FXML
    private TextField timeslotEndTF;

    @FXML
    private Button timeslotRemoveBtn;

    @FXML
    private TextField timeslotStartTF;

    @FXML
    void onAddTimeslot(ActionEvent event) {
        
    }

    @FXML
    void onEdit(ActionEvent event) {
        subjectTA.setEditable(true);
        saveChangesBtn.setVisible(true);
        editBtn.setVisible(false);
    }

    @FXML
    void onSaveChanges(ActionEvent event) {
        String newSubjects = subjectTA.getText();
        String tokens[] = newSubjects.split(",");
        ArrayList<String> subjects = new ArrayList<String>();
        for(String i : tokens){
            subjects.add(i);
        }
        db.getStudent(Statics.studentID).setSubject(subjects);
        subjectTA.setEditable(false);
        subjectTA.setPromptText(db.getStudent(Statics.studentID).toString());
        editBtn.setVisible(true);
        saveChangesBtn.setVisible(false);
    }

    @FXML
    void onRemove(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = new Database();
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teacherDB.csv");

        saveChangesBtn.setVisible(false);
        editBtn.setVisible(true);
        subjectTA.setEditable(false);
        timeslotRemoveBtn.setVisible(false);
    }
}

