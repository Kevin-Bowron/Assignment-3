package com.example.assignment_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("Menu-Scene.fxml"));
        WordGuesserController controller = (WordGuesserController)loader.getController();
        WordGuesserController.applicationStage = primaryStage;
        Scene scene = new Scene(root, 320, 240);
        primaryStage.setTitle("Word Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}