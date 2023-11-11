package com.example.nushhack23.controller;

import com.example.nushhack23.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button logInBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    void onLogIn(ActionEvent event) {
        MainApplication.startStage.hide();
        MainApplication.loginStage.show();
    }

    @FXML
    void onSignUp(ActionEvent event) {
        MainApplication.startStage.hide();
        MainApplication.signupStage.show();
    }
}

