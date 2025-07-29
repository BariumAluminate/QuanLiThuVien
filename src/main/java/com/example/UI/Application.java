package com.example.UI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Application extends javafx.application.Application {
  @Override
  public void start(Stage stage) throws FileNotFoundException {
    Image image = new Image(new FileInputStream("src/main/java/com/example/UI/Assets/0_cqPWt_uqeZgPWRby.jpg"));
    ImageView imageView = new ImageView(image);

    imageView.setX(0);
    imageView.setY(0);


    StackPane box = new StackPane();

    SignIn signIn = new SignIn();
    signIn.render(box);

    box.getChildren().addFirst(imageView);

    Scene scene = new Scene(box, 1280, 700);

    signIn.fit_to_screen(scene);
    imageView.fitWidthProperty().bind(box.widthProperty());
    imageView.fitHeightProperty().bind(box.heightProperty());
    imageView.setPreserveRatio(false);


    stage.setTitle("Library Management");
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
      launch(args);
  }
}