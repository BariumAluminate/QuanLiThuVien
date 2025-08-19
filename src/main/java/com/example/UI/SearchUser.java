package com.example.UI;

import javafx.scene.control.Button;

public class SearchUser extends SingleLineQuery{
    public SearchUser() {
        super("Find an user");
        Element.setPromptText("Enter the user's ID");
    }
}
