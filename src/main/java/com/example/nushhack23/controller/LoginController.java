package com.example.nushhack23.controller;

import com.example.nushhack23.MainApplication;
import com.example.nushhack23.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField idTF;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField pwdTF;

    @FXML
    private CheckBox teacherBox;

    @FXML
    void onLogin(ActionEvent event) {
        Database db = new Database();
        boolean found = false;
        NUSHFella user = null;
        db.loadStudentDB("studentsDB.csv");
        db.loadTeacherDB("teachersDB.csv");
        if(teacherBox.isSelected()){
            for(Teacher i: db.getTeacherDB()){
                if(i.getId().equals(idTF.getText())){
                    found = true;
                    user = i;
                    break;
                }
            }
        } else {
            for(Student i: db.getStudentDB()){
                System.out.println(i.getId());
                if(i.getId().equals(idTF.getText())){
                    found = true;
                    user = i;
                    break;
                }
            }
        } if(!found) {
            showAlert("Error!", "User not found!", "Enter valid user id");
            return;
        } else {
            if(!user.getPassword().equals(pwdTF.getText())){
                showAlert("Error!", "Wrong password!", "Enter correct password");
                return;
            }
        }
        Statics.studentID = idTF.getText();
        if(teacherBox.isSelected()){
            MainApplication.teacherStage.show();
        } else{
            MainApplication.studentStage.show();
        }
        MainApplication.loginStage.hide();
    }
    private void showAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
