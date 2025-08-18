package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import com.example.quanlithuvien.UserService;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Controller {
    private final StackPane mainBox;
    private final Scene scene;
    private Reader reader;
    Book book = new Book("123", "test", "test", "test");

    public Controller(StackPane stackPane, Scene scene) {
        this.mainBox = stackPane;
        this.scene = scene;
    }

    public void showSignIn() {
        mainBox.getChildren().clear();
        SignIn signIn = new SignIn();
        signIn.render(mainBox);
        signIn.fit_to_screen(scene);
        mainBox.requestLayout();
        signIn.setOnAction(()->{
            String name = signIn.getUserName();
            String password = signIn.getPassWord();
            String ID = signIn.getId();
            reader = new Reader(ID, name, password);
            showUserInterface();
//            if (UserService.isLibrarian())
//            try {
//                System.out.println(ID + " " + password + " " + name);
//                if (UserService.login(reader)) {
//                    showUserInterface();
//                }
//            } catch (IOException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        });
        System.out.println("SignIn UI added to mainBox. Children: " + mainBox.getChildren());
    }

    public void showUserInterface() {
        mainBox.getChildren().clear();
        UserInterface userInterface = new UserInterface(reader.getName(), this::showSignIn);
        userInterface.render(mainBox);
        userInterface.resizeAll(mainBox);
        userInterface.setOnAction(mainBox, reader);
        System.out.println("UserInterface UI added to mainBox. Children: " + mainBox.getChildren());
    }
}