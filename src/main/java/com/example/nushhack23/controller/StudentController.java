package com.example.nushhack23.controller;

import com.example.nushhack23.MainApplication;
import com.example.nushhack23.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import static com.example.nushhack23.MainApplication.*;

public class StudentController implements Initializable {

    private Database db;
    private ObservableList<Teacher> tableList = FXCollections.observableArrayList();
    private Teacher selectedTeacher;

    @FXML
    private Button editBtn;

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
    private Button saveChangesBtn;


    @FXML
    private TextField subjectTF;

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
    private FlowPane teacherTimeslots;

    @FXML
    private TableView<Teacher> timeslotTV;

    @FXML
    private TableColumn<Teacher, String> idColumn;

    @FXML
    private TableColumn<Teacher, String> nameColumn;

    @FXML
    private TableColumn<Teacher, String> starsColumn;


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
        ArrayList<String> subjects = new ArrayList<>(Arrays.asList(tokens));
        db.getStudent(Statics.studentID).setSubject(subjects);
        subjectTF.setEditable(false);
        subjectTF.setPromptText(db.getStudent(Statics.studentID).toString());
        editBtn.setVisible(true);
        saveChangesBtn.setVisible(false);
    }

    @FXML
    void onTableClicked(MouseEvent event) {
        selectedTeacher = timeslotTV.getSelectionModel().getSelectedItem();
        teacherName.setText("Name: " + selectedTeacher.getName());
        teacherID.setText("ID: " + selectedTeacher.getId());
        teacherHours.setText("Hours: " + String.format("%.2f", selectedTeacher.getHours()));
        teacherStars.setText("Stars: " + String.format("%.2f", selectedTeacher.getStars()));
        teacherSubjects.setText("Subjects: " + selectedTeacher.getSubjectsString());
        updateTimeslots();
    }

    @FXML
    void onLogOut(ActionEvent event) {
        db.writeStudents("studentsDB.csv");
        db.writeTeachers("teachersDB.csv");
        Statics.studentID = "";
        logOutBtn.getScene().getWindow().hide();
        startStage.show();
    }

    void onTimeslot(ActionEvent event) {
        Timeslot timeslot = null;
        for(Timeslot i: selectedTeacher.getAvailableTimeslots()){
            if(i.toString().equals(((Button)event.getSource()).getText())){
                timeslot = i;
                break;
            }
        }
        selectedTeacher.bookTimeslot(timeslot, db.getStudent(Statics.studentID));
        updateTimeslots();
    }

    public void start(WindowEvent event) {
        selectedTeacher = null;
        db = new Database();
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teachersDB.csv");

        NUSHFella me = db.getStudent(Statics.studentID);
        myName.setText("Name: " + me.getName());
        myId.setText("ID: " + me.getId());
        myHours.setText("Hours: " + String.format("%.2f", me.getHours()));
        myStars.setText("Stars: " + String.format("%.2f", me.getStars()));

        saveChangesBtn.setVisible(false);
        subjectTF.setEditable(false);
        subjectTF.setText(db.getStudent(Statics.studentID).getSubjectsString());

        idColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        starsColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("stars"));
        timeslotTV.setItems(tableList);
        Student s1 = db.getStudent(Statics.studentID);
        for(Teacher t1 : db.getTeacherDB()){
            for(String subject : s1.getSubjects()){
                for(String subject2: t1.getSubjects()){
                    if(subject.equals(subject2)){
                        tableList.add(t1);
                        break;
                    }
                }
                if(tableList.get(tableList.size()-1) == t1)
                    break;
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentStage.setOnShown(this::start);
    }

    private void updateTimeslots() {
        teacherTimeslots.getChildren().clear();
        for(Timeslot i: selectedTeacher.getAvailableTimeslots()) {
            Button btn = new Button();
            btn.setText(i.toString());
            btn.setOnAction(this::onTimeslot);
            teacherTimeslots.getChildren().add(btn);
        }
    }
}
