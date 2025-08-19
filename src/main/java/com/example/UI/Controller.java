package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import com.example.quanlithuvien.UserService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Controller {
    private final StackPane mainBox;
    private final Scene scene;
    private Reader reader;

    public Controller(StackPane stackPane, Scene scene) {
        this.mainBox = stackPane;
        this.scene = scene;
    }

    public Controller(StackPane stackPane, Scene scene, Reader reader) {
        this.mainBox = stackPane;
        this.scene = scene;
        this.reader = reader;
    }

    public void showSignIn() {
        mainBox.getChildren().clear();
        SignIn signIn = new SignIn();
        signIn.render(mainBox);
        signIn.fit_to_screen(scene);
        mainBox.requestLayout();
        signIn.setOnAction(() -> {
            String name = signIn.getUserName();
            String password = signIn.getPassWord();
            String ID = signIn.getId();
            reader = new Reader(ID, name, password);
            try {
                System.out.println(ID + " " + password + " " + name);
                if (UserService.login(reader)) {
                    System.out.println(reader.getApi_KEY());
//                    if (UserService.isLibrarian(reader)) {
//                        String API_key = reader.getApi_KEY();
//                        String cr = reader.getCsrftoken();
//                        reader = new Librarian(ID, name, password, API_key, cr);
//                    }
                    showUserInterface();
                } else {
                    showAlert("Login Failed", "Invalid credentials. Please try again.");
                }
            } catch (IOException | InterruptedException e) {
                showAlert("Error", "An error occurred during login: " + e.getMessage());
            }
        });
        System.out.println("SignIn UI added to mainBox. Children: " + mainBox.getChildren());
    }

    public void showUserInterface() {
        try {
            mainBox.getChildren().clear();
            UserInterface userInterface = new UserInterface(reader, this::showSignIn);
            userInterface.render(mainBox);
            userInterface.resizeAll(mainBox);
            userInterface.setOnAction(mainBox);
            System.out.println("UserInterface UI added to mainBox. Children: " + mainBox.getChildren());
        } catch (IOException e) {
            showAlert("Error", "Failed to load User Interface: " + e.getMessage());
        } catch (InterruptedException e) {
            showAlert("Error", "Operation interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}