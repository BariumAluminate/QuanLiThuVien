package com.example.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class SignIn {
  private final TextField ID ;//ID field
  private final PasswordField password;//password field
  private final Label label1;//id label
  private final Label label2;//password label.
  private final Label nameLabel;
  private final Button LogIn;
  private final Rectangle rect;//the background rect.
  private final TextField name;

    /**
     * Constructor.
     */
  public SignIn() {
      ID = new TextField();
      ID.setPromptText("Enter your ID");
      ID.setStyle("-fx-font-size: 30");
      ID.setPrefHeight(60);
      ID.setPrefWidth(500);

      password = new PasswordField();
      password.setPromptText("Enter your Password");
      password.setStyle("-fx-font-size: 30");
      password.setPrefHeight(60);
      password.setPrefWidth(500);

      name = new TextField();
      name.setPromptText("Enter your registered name");
      name.setStyle("-fx-font-size: 30");
      name.setPrefHeight(60);
      name.setPrefWidth(500);

      label1 = new Label("ID:");
      label1.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

      label2 = new Label("Password:");
      label2.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

      nameLabel = new Label("Your registered name:");
      nameLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

      LogIn = new Button("Sign In");
      LogIn.setStyle("-fx-font-size: 30");

      rect = new Rectangle();
  }

    /**
     *add the layout of the signin page to scene variable.
     */
  public void render(StackPane stack) {
      //Create a box to put the label inside, so that we can align it to the left
      HBox hbox1 = new HBox(label1);
      hbox1.setAlignment(Pos.CENTER_LEFT);

      HBox hbox2 = new HBox(label2);
      hbox2.setAlignment(Pos.CENTER_LEFT);

      HBox hbox3 = new HBox(nameLabel);
      hbox3.setAlignment(Pos.CENTER_LEFT);

      //make a box to put all the components but the rectangle into it
      VBox box = new VBox();
      box.setPadding(new Insets(2));
      box.setAlignment(Pos.CENTER);
      box.setMaxWidth(640);

      box.getChildren().addAll(hbox1, ID, hbox2, password, hbox3, name, LogIn);

      stack.getChildren().addAll(rect, box);

      rect.setWidth(300);
      rect.setHeight(300);
      rect.setFill(Color.rgb(255, 255, 255, 0.75)); // light blue with 50% opacity
      rect.setStroke(null);
      rect.setArcWidth(20);
      rect.setArcHeight(20);
  }

  public void fit_to_screen(Scene scene) {
      rect.widthProperty().bind(scene.widthProperty().multiply(0.6));
      rect.heightProperty().bind(scene.heightProperty().multiply(0.6));
  }
}
