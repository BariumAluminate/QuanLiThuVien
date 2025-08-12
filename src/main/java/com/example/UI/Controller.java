package com.example.UI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Controller {
    private final StackPane mainBox;
    private final Scene scene;

    public Controller(StackPane stackPane, Scene scene) {
        this.mainBox = stackPane;
        this.scene = scene;
    }

    public void showSignIn() {
        mainBox.getChildren().clear();
        SignIn signIn = new SignIn(this::showUserInterface);
        signIn.render(mainBox);
        signIn.fit_to_screen(scene);
        mainBox.requestLayout();
        System.out.println("SignIn UI added to mainBox. Children: " + mainBox.getChildren());
    }

    public void showUserInterface() {
        mainBox.getChildren().clear();
        UserInterface userInterface = new UserInterface("John", this::showSignIn);
        userInterface.render(mainBox);
        userInterface.resizeAll(mainBox);
        System.out.println("UserInterface UI added to mainBox. Children: " + mainBox.getChildren());
    }
}