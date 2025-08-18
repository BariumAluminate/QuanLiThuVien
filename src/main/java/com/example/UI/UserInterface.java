package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.BookService;
import com.example.quanlithuvien.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class UserInterface {
    private final VBox userFace;

    private final VBox functionBox;
    private final Button addButton;
    private final Button removeButton;
    private final Button searchButton;
    private final Button showButton;

    private final HBox objectBox;
    private final Button updateButton;
    private Reader user;

    private String editMode;

    private TableView<Book> table;

    private Book book;

    UserInterface(Reader reader, Runnable onSignOut) throws IOException, InterruptedException {
        editMode = "book";

        // User block
        userFace = new VBox();
        userFace.setStyle("-fx-background-color: White;");
        userFace.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        Text name = new Text(reader.getName());
        name.setStyle("-fx-font-size: 30");
        userFace.getChildren().add(name);
        Button signOut = new Button("Sign out");
        signOut.setOnAction(e->onSignOut.run());
        signOut.setStyle("-fx-font-size: 20");
        userFace.getChildren().add(signOut);
        userFace.getStyleClass().add("UserBox");

        //Function Button block
        functionBox = new VBox();
        functionBox.setStyle("-fx-background-color: deepskyblue;");
        functionBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        addButton = new Button("Add");
        addButton.setMaxHeight(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        VBox.setVgrow(addButton, Priority.ALWAYS);
        addButton.getStyleClass().add("functionButton");

        removeButton = new Button("Remove");
        removeButton.setMaxWidth(Double.MAX_VALUE);
        removeButton.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(removeButton, Priority.ALWAYS);
        VBox.setVgrow(removeButton, Priority.ALWAYS);
        removeButton.getStyleClass().add("functionButton");

        updateButton = new Button("Update");
        updateButton.setMaxHeight(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(updateButton, Priority.ALWAYS);
        VBox.setVgrow(updateButton, Priority.ALWAYS);
        updateButton.getStyleClass().add("functionButton");

        searchButton = new Button("Search");
        searchButton.setMaxHeight(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchButton, Priority.ALWAYS);
        VBox.setVgrow(searchButton, Priority.ALWAYS);
        searchButton.getStyleClass().add("functionButton");

        showButton = new Button("Show");
        showButton.setMaxHeight(Double.MAX_VALUE);
        showButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(showButton, Priority.ALWAYS);
        VBox.setVgrow(showButton, Priority.ALWAYS);
        showButton.getStyleClass().add("functionButton");

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        Separator sep4 = new Separator(Orientation.HORIZONTAL);

        functionBox.getChildren().addAll(addButton, sep1,removeButton, sep2);
        functionBox.getChildren().addAll(updateButton, sep3, searchButton, sep4, showButton);

        //Object to choose
        objectBox = new HBox();
        objectBox.setStyle("-fx-background-color: deepskyblue;");
        objectBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Button bookButton = new Button("Book");
        bookButton.setMaxHeight(Double.MAX_VALUE);
        bookButton.setMaxWidth(Double.MAX_VALUE);
        bookButton.setOnAction(e-> {
            if (bookButton.getText() == "Book") {
                editMode = "User";
                bookButton.setText("User");
            }
            else {
                editMode = "Book";
                bookButton.setText("Book");
            }
        });
        HBox.setHgrow(bookButton, Priority.ALWAYS);
        VBox.setVgrow(bookButton, Priority.ALWAYS);

        objectBox.getChildren().addAll(bookButton);

        bookButton.getStyleClass().add("objectButton");

        user = reader;

        table = new TableView<>();

        // Define the borrow button column
        table = new TableView<>();
        ObservableList<Book> bookList = FXCollections.observableArrayList(BookService.showAllBook(user));
        table.setItems(bookList);

        // Define table columns
        TableColumn<Book, String> Id = new TableColumn<>("ID");
        Id.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());

        TableColumn<Book, String> title = new TableColumn<>("Title");
        title.setCellValueFactory(cellData -> cellData.getValue().bookTitle());

        TableColumn<Book, String> author = new TableColumn<>("Author");
        author.setCellValueFactory(cellData -> cellData.getValue().bookAuthor());

        // Borrow button column
        TableColumn<Book, Void> borrowButton = new TableColumn<>("Borrow");
        borrowButton.setCellFactory(param -> new TableCell<Book, Void>() {
            private final Button button = new Button("Borrow");

            {
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("functionButton"); // Match styling with other buttons
                button.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    try {
                        user.borrow(book.getBookId()); // Call the borrow method on the Reader object
                        table.refresh(); // Refresh table to reflect any changes
                        // Optional: Show success message
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Borrow Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Book borrowed successfully!");
                        alert.showAndWait();
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Borrow Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to borrow book: " + e.getMessage());
                        alert.showAndWait();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        });

        // Add columns to the TableView
        table.getColumns().addAll(Id, title, author, borrowButton);
    }

    public void resizeUserFace(StackPane stackPane) {
        userFace.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        userFace.prefHeightProperty().bind(stackPane.heightProperty().divide(4));
    }

    public void resizeFunctionBox(StackPane stackPane) {
        functionBox.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        functionBox.prefHeightProperty().bind(stackPane.heightProperty().divide(4).multiply(3));
    }

    public void resizeObjectBox(StackPane stackPane) {
        objectBox.prefWidthProperty().bind(stackPane.widthProperty().divide(4).multiply(3));
        objectBox.prefHeightProperty().bind(stackPane.heightProperty().divide(8));
    }

    public void resizeAll(StackPane stackPane) {
        resizeUserFace(stackPane);
        resizeObjectBox(stackPane);
        resizeFunctionBox(stackPane);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(userFace, Pos.TOP_LEFT);
        stackPane.getChildren().addFirst(userFace);
        userFace.setAlignment(Pos.CENTER);

        StackPane.setAlignment(functionBox, Pos.BOTTOM_LEFT);
        stackPane.getChildren().add(functionBox);
        functionBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(objectBox, Pos.TOP_RIGHT);
        stackPane.getChildren().add(objectBox);
        objectBox.setAlignment(Pos.CENTER);
    }

    public void removeQuery(StackPane stackPane, Reader user) {
        removeQuery remove = new removeQuery();
        remove.resizeProperty(stackPane);
        remove.render(stackPane);
    }

    public void addQuery(StackPane stackPane, Reader user) throws IOException, InterruptedException {
        AddQuery add = new AddQuery();
        add.setOnAction(stackPane, user);
        add.render(stackPane);
        add.resize(stackPane);

    }

    public void searchQuery(StackPane stackPane, Reader user) {
        SearchChooser searchChooser = new SearchChooser();
        searchChooser.render(stackPane);
        searchChooser.resize(stackPane);
        searchChooser.setOnAction(stackPane, user);
    }

    public void updateQuery(StackPane stackPane, Reader user, Book book) {
//        if (user instanceof  Librarian) {
//            UpdateChooser update = new UpdateChooser();
//            update.render(stackPane);
//            update.resize(stackPane);
//            update.setOnAction(stackPane, user, null);
//        }
        UpdateChooser update = new UpdateChooser();
        update.render(stackPane);
        update.resize(stackPane);
        update.setOnAction(stackPane, user, book);
    }

    public void showQuery(StackPane stackPane, Reader user, Book book) {
        ShowBookInfo showBookInfo = new ShowBookInfo(book);
        showBookInfo.render(stackPane);
        showBookInfo.resize(stackPane);
        showBookInfo.setup(stackPane);
    }

    public void setOnAction(StackPane stackPane, Reader reader) {
        addButton.setOnAction(e-> {
            try {
                addQuery(stackPane, reader);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e->removeQuery(stackPane, reader));
        searchButton.setOnAction(e->searchQuery(stackPane, reader));
        updateButton.setOnAction(e->updateQuery(stackPane, reader, book));
        showButton.setOnAction(e->showQuery(stackPane, reader, book));
    }
}