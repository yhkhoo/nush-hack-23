package com.example.nushhack23.controller;

import com.example.nushhack23.model.Database;
import com.example.nushhack23.model.Rating;
import com.example.nushhack23.model.Statics;
import com.example.nushhack23.model.Teacher;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<?> availableTimeslotsTV;

    @FXML
    private TableView<?> bookedTimeSlotsTV;

    @FXML
    private TextField commentTF;

    @FXML
    private Button completeBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button leaveCommentBtn;

    @FXML
    private Button logOutBtn;

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
    private Slider ratingSL;

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
    private TextField subjectTF;

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
        subjectTF.setEditable(true);
        saveChangesBtn.setVisible(true);
        editBtn.setVisible(false);
    }

    @FXML
    void onSaveChanges(ActionEvent event) {
        String newSubjects = subjectTF.getText();
        String tokens[] = newSubjects.split(",");
        ArrayList<String> subjects = new ArrayList<String>();
        for(String i : tokens){
            subjects.add(i);
        }
        db.getStudent(Statics.studentID).setSubject(subjects);
        subjectTF.setEditable(false);
        subjectTF.setPromptText(db.getStudent(Statics.studentID).toString());
        editBtn.setVisible(true);
        saveChangesBtn.setVisible(false);
    }

    @FXML
    void onComplete(ActionEvent event) {

    }

    @FXML
    void onRemove(ActionEvent event) {
        availableTimeslotsTV.getSelectionModel().getSelectedItem();
    }

    @FXML
    void onLeaveComment(ActionEvent event) {
        if(commentTF.getText().isEmpty()){
            showAlert("Invalid input", "Please leave a comment", "");
        }else {
            String comment = commentTF.getText();
            double rating = ratingSL.getValue();
            db.getTeacher(Statics.studentID).leaveRemark(db.getStudent(studentID.getText()), new Rating(rating, comment));
        }
    }

    @FXML
    void onLogOut(ActionEvent event) {

    }

    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        db = new Database();
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teacherDB.csv");

        saveChangesBtn.setVisible(false);
        editBtn.setVisible(true);
        subjectTF.setEditable(false);
        timeslotRemoveBtn.setVisible(false);


    }
}

