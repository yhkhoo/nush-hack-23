package com.example.nushhack23.controller;

import com.example.nushhack23.MainApplication;
import com.example.nushhack23.model.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.nushhack23.MainApplication.startStage;

public class TeacherController implements Initializable {

    private Database db;

    private ObservableList<BookedTimeslot> bookedList = FXCollections.observableArrayList();

    @FXML
    private Button addTimeslotBtn;

    @FXML
    private TableColumn<?, ?> availableDurationColumn;

    @FXML
    private TableColumn<?, ?> availableEndColumn;

    @FXML
    private TableColumn<?, ?> availableStartColumn;

    @FXML
    private TableView<?> availableTimeslotsTV;

    @FXML
    private TableColumn<BookedTimeslot, String> bookedEndColumn;

    @FXML
    private TableColumn<BookedTimeslot, String> bookedIdColumn;

    @FXML
    private TableColumn<BookedTimeslot, String> bookedNameColumn;

    @FXML
    private TableColumn<BookedTimeslot, String> bookedStartColumn;

    @FXML
    private TableView<BookedTimeslot> bookedTimeSlotsTV;

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
        db.writeStudents("studentsDB.csv");
        db.writeTeachers("teachersDB.csv");
        Statics.studentID = "";
        logOutBtn.getScene().getWindow().hide();
        startStage.show();
    }

    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void start(WindowEvent event) {
        db = new Database();
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teachersDB.csv");

        saveChangesBtn.setVisible(false);
        editBtn.setVisible(true);
        subjectTF.setEditable(false);
        timeslotRemoveBtn.setVisible(false);

        NUSHFella me = db.getTeacher(Statics.studentID);
        myName.setText("Name: " + me.getName());
        myId.setText("ID: " + me.getId());
        myHours.setText("Hours: " + String.format("%.2f", me.getHours()));
        myStars.setText("Stars: " + String.format("%.2f", me.getStars()));
        mySubjects.setText("Subjects: " + me.getSubjectsString());

        bookedStartColumn.setCellValueFactory(new PropertyValueFactory<BookedTimeslot, String>("start"));
        bookedEndColumn.setCellValueFactory(new PropertyValueFactory<BookedTimeslot, String>("end"));
        bookedNameColumn.setCellValueFactory(new PropertyValueFactory<BookedTimeslot, String>("studentName"));
        bookedIdColumn.setCellValueFactory(new PropertyValueFactory<BookedTimeslot, String>("studentId"));
        updateBookedTimeslots();
        bookedTimeSlotsTV.setItems(bookedList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainApplication.teacherStage.setOnShown(this::start);
    }

    private void updateBookedTimeslots() {
        bookedList.clear();
        bookedList.addAll(db.getTeacher(Statics.studentID).getBookedTimeslots());
    }
}

