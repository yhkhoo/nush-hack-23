package com.example.nushhack23.controller;

import com.example.nushhack23.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML
    private TextField nameTF;

    @FXML
    private TextField idTF;

    @FXML
    private TextField pwdTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private CheckBox teacherBox;

    @FXML
    void onSignUp(ActionEvent event) {
        Database db = new Database();
        if(idTF.getText().isEmpty() || nameTF.getText().isEmpty() || pwdTF.getText().isEmpty()){
            showAlert("Error!", "Fields empty!", "Enter values for the fields");
            return;
        }
        if(!NUSHFella.validPassword(pwdTF.getText())){
            showAlert("Error!", "Invalid password!", "Password must be at least 8 and at most 16 characters, and must contain a digit, a symbol, and an uppercase letter.");
            return;
        }
        if(teacherBox.isSelected()){
            db.loadTeacherDB("teachersDB.csv");
            db.getTeacherDB().add(new Teacher(idTF.getText(), nameTF.getText(), pwdTF.getText()));
        } else {
            db.loadStudentDB("studentDB.csv");
            db.getStudentDB().add(new Student(idTF.getText(), nameTF.getText(), pwdTF.getText()));
        }

    }

    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
