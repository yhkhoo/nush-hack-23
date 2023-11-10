package com.example.nushhack23;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    private Stage studentStage;
    private Stage teacherStage;
    private Stage loginStage;
    private Stage signupStage;
    private Stage startStage;
    @Override
    public void init() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        studentStage = new Stage(StageStyle.DECORATED);
        studentStage.setTitle("NUSHhub");
        studentStage.setScene(new Scene(load("student")));
        teacherStage = new Stage(StageStyle.DECORATED);
        teacherStage.setTitle("NUSHhub");
        teacherStage.setScene(new Scene(load("teacher")));
        loginStage = new Stage(StageStyle.DECORATED);
        loginStage.setTitle("NUSHhub");
        loginStage.setScene(new Scene(load("login")));
        signupStage = new Stage(StageStyle.DECORATED);
        signupStage.setTitle("NUSHhub");
        signupStage.setScene(new Scene(load("signup")));
        startStage = new Stage(StageStyle.DECORATED);
        startStage.setTitle("NUSHhub");
        startStage.setScene(new Scene(load("start")));
        startStage.show();
    }

    private Parent load(String fn) throws IOException {
        return FXMLLoader.load(getClass().getResource("view/"+fn+"-view.fxml"));
    }
}
