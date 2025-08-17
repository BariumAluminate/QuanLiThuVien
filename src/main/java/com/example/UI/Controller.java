package com.example.UI;

import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import com.example.quanlithuvien.UserService;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Controller {
    private final StackPane mainBox;
    private final Scene scene;
    private Librarian librarian;
    private Reader reader;

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
            if (UserService.login(reader) == true) {

            }
        });
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