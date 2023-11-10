package com.example.nushhack23.controller;

import com.example.nushhack23.model.Database;
import com.example.nushhack23.model.Statics;
import com.example.nushhack23.model.Student;
import com.example.nushhack23.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    private Database db;
    private ObservableList<Teacher> tableList = FXCollections.observableArrayList();
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
    private TableView<Teacher> timeslotTV;

    @FXML
    private TableColumn<Teacher, String> idColumn;

    @FXML
    private TableColumn<Teacher, String> nameColumn;

    @FXML
    private TableColumn<Teacher, String> starsColumn;

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
}
