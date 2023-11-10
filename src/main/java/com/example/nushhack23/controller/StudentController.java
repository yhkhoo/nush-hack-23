package com.example.nushhack23.controller;

import com.example.nushhack23.model.Database;
import com.example.nushhack23.model.Statics;
import com.example.nushhack23.model.Student;
import com.example.nushhack23.model.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    private Database db;
    @FXML
    private Button bookBtn;

    @FXML
    private Button editBtn;

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
    private TextArea subjectTA;

    @FXML
    private Label teacherHours;

    @FXML
    private Label teacherID;

    @FXML
    private Label teacherName;

    @FXML
    private ImageView teacherPfp;

    @FXML
    private Label teacherStars;

    @FXML
    private Label teacherSubjects;

    @FXML
    private Label teacherTimeslots;

    @FXML
    private TableView<?> timeslotTV;

    @FXML
    void onBook(ActionEvent event) {
        
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = new Database();
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teacherDB.csv");

        saveChangesBtn.setVisible(false);
        subjectTA.setEditable(false);
        subjectTA.setPromptText(db.getStudent(Statics.studentID).getSubjects().toString());

        Student s1 = db.getStudent(Statics.studentID);
        for(Teacher t1 : db.getTeacherDB()){
            for(String subject : t1.getSubjects()){
                if(s1.getSubjects().contains(subject)){

                }
            }
        }
    }
}
