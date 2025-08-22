package com.example.UI;

import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import com.example.quanlithuvien.UserService;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        StackPane backgroundLayer = new StackPane();
        Image image = new Image(new FileInputStream("src/main/java/com/example/UI/Assets/0_cqPWt_uqeZgPWRby.jpg"));
        ImageView imageView = new ImageView(image);
        backgroundLayer.getChildren().add(imageView);

        // UI layer (content changes here)
        StackPane uiLayer = new StackPane();
        uiLayer.setStyle("-fx-background-color: transparent;");

        // Main container
        StackPane mainContainer = new StackPane();
        mainContainer.getChildren().addAll(backgroundLayer, uiLayer);

        Scene scene = new Scene(mainContainer, 1280, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        // Bind image to background
        imageView.fitWidthProperty().bind(backgroundLayer.widthProperty());
        imageView.fitHeightProperty().bind(backgroundLayer.heightProperty());
        imageView.setPreserveRatio(false);

        // Create application controller with UI layer only
        Controller controller = new Controller(uiLayer, scene);
        controller.showSignIn(); // Start with sign-in
        stage.setTitle("Library Management");
        stage.setScene(scene);
        stage.show();
    }
      public static void main(String[] args) {
        launch(args);
      }
}