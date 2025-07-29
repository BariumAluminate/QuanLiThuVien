package com.example.UI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {
  @Override
  public void start(Stage stage) {
    StackPane box = new StackPane();

    SignIn signIn = new SignIn();
    signIn.render(box);

    Scene scene = new Scene(box, 1280, 700);

    signIn.fit_to_screen(scene);

    stage.setTitle("Library Management");
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
      launch(args);
  }
}