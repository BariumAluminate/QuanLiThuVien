package com.example.UI;

import com.example.quanlithuvien.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private final TableView<Book> table;

    ObservableList<Book> bookList;

    private Book book;

    UserInterface(Reader reader, Runnable onSignOut) throws IOException, InterruptedException {
        editMode = "Book";

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
            if (Objects.equals(bookButton.getText(), "Book")) {
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
        System.out.println(user.getPassword());
        System.out.println(user.getApi_KEY());

        table = new TableView<>();

        bookList = FXCollections.observableArrayList(user.showAllBook());
        table.setItems(bookList);

// Define table columns
        TableColumn<Book, String> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
        TableColumn<Book, String> title = new TableColumn<>("Title");
        title.setCellValueFactory(cellData -> cellData.getValue().bookTitle());
        TableColumn<Book, String> author = new TableColumn<>("Author");
        author.setCellValueFactory(cellData -> cellData.getValue().bookAuthor());
        TableColumn<Book, Void> borrowButton = new TableColumn<>("Borrow");
    borrowButton.setCellFactory(
        param ->
            new TableCell<>() {
              private final Button button = new Button("Borrow");

              {
                button.setMaxWidth(Double.MAX_VALUE);
                button.getStyleClass().add("functionButton");
              }

              @Override
              protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                  setGraphic(null);
                } else {
                  Book book = getTableView().getItems().get(getIndex());
                  button.setOnAction(
                      event -> {
                        if (book.getBorrowedId().isEmpty()){
                          try {
                            user.borrow(book.getBookId());
                            table.refresh();
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
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Borrow Error");
                            alert.setHeaderText(null);
                            alert.setContentText("This book is borrowed");
                            alert.showAndWait();
                        }
                      });
                  setGraphic(button);
                }
              }
            });

        table.getColumns().addAll(id, title, author, borrowButton);
        table.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        id.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        title.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        author.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        borrowButton.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

        // Selection listener to copy selected book to chosenBook
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                book = newSelection;
                System.out.println("Selected book copied to chosenBook: " + book.getTitle());
            } else {
                book = null;
                System.out.println("No book selected, chosenBook set to null");
            }
        });
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

    public void resizeTable(StackPane stackPane) {
        table.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.75)); // 75% width
        table.prefHeightProperty().bind(stackPane.heightProperty().subtract(objectBox.prefHeightProperty())); // Full height minus objectBox
    }

    public void resizeAll(StackPane stackPane) {
        resizeUserFace(stackPane);
        resizeObjectBox(stackPane);
        resizeFunctionBox(stackPane);
        resizeTable(stackPane);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(userFace, Pos.TOP_LEFT);
        stackPane.getChildren().addFirst(userFace);
        userFace.setAlignment(Pos.CENTER);

        StackPane.setAlignment(functionBox, Pos.BOTTOM_LEFT); // Anchor functionBox to left center, full height
        stackPane.getChildren().add(functionBox);
        functionBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(objectBox, Pos.TOP_RIGHT);
        stackPane.getChildren().add(objectBox);
        objectBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(table, Pos.CENTER_RIGHT); // Align table to right center
        stackPane.getChildren().add(table);

        // Bind table's left margin to functionBox width for direct contact
        functionBox.widthProperty().addListener((obs, oldValue, newValue) -> {
            StackPane.setMargin(table, new javafx.geometry.Insets(objectBox.getHeight(), 0, 0, 0)); // Top margin for objectBox, no left gap
        });

        // Bind top margin for table to objectBox height
        objectBox.heightProperty().addListener((obs, oldValue, newValue) -> {
            StackPane.setMargin(table, new javafx.geometry.Insets(newValue.doubleValue(), 0, 0, 0));
        });
    }

    public void removeQuery(StackPane stackPane) throws IOException, InterruptedException {
        if (Objects.equals(editMode, "User")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Librarian Authority:");
            alert.setHeaderText(null);
            alert.setContentText("You can't do this in this mode");
            alert.showAndWait();
            return;
        }
        if (librarianAuthority()) return;
        System.out.println("Check successfully");
        RemoveQuery remove = new RemoveQuery();
        remove.render(stackPane);
        remove.resizeProperty(stackPane);
        remove.setOnAction(stackPane, user, () -> {
            try {
              refresh();
            } catch (IOException | InterruptedException ex) {
              throw new RuntimeException(ex);
            }
        });
        System.out.println("Render successfully");
    }

    public void addQuery(StackPane stackPane) throws IOException, InterruptedException {
        if (Objects.equals(editMode, "Book")) {
            if (librarianAuthority()) return;
            AddQuery add = new AddQuery();
            add.setOnAction(stackPane, user, ()-> {
                try {
                    refresh();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            add.render(stackPane);
            add.resize(stackPane);
        } else {
            if (librarianAuthority()) return;
            AddUser addUser = new AddUser();
            addUser.setOnAction(stackPane, user);
            addUser.render(stackPane);
            addUser.resize(stackPane);
        }

    }

    private boolean librarianAuthority() {
        if (!(user instanceof Librarian)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Librarian Authority:");
            alert.setHeaderText(null);
            alert.setContentText("You are not a librarian to perform this action");
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public void searchQuery(StackPane stackPane) throws IOException, InterruptedException {
        if (Objects.equals(editMode, "Book")) {
            SearchChooser searchChooser = new SearchChooser();
            searchChooser.render(stackPane);
            searchChooser.resize(stackPane);
            searchChooser.setOnAction(stackPane, user, table::refresh, bookList);
        }
        else {
            SearchUser searchUser = new SearchUser();
            searchUser.render(stackPane);
            searchUser.resizeProperty(stackPane);
        }
    }

    public void updateQuery(StackPane stackPane) throws IOException, InterruptedException {
        if (librarianAuthority()) return;
        UpdateChooser update = new UpdateChooser();
        update.render(stackPane);
        update.resize(stackPane);
        update.setOnAction(stackPane, user, book, ()-> {
            try {
                refresh();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void showQuery(StackPane stackPane) {
        ShowBookInfo showBookInfo = new ShowBookInfo(book);
        showBookInfo.render(stackPane);
        showBookInfo.resize(stackPane);
        showBookInfo.setup(stackPane);
    }

    public void refresh() throws IOException, InterruptedException {
        bookList = FXCollections.observableArrayList(BookService.showAllBook(user));
        table.setItems(bookList);
        table.refresh();
    }

    public void setOnAction(StackPane stackPane) {
        addButton.setOnAction(e-> {
            try {
                addQuery(stackPane);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        removeButton.setOnAction(e->{
            try {
                removeQuery(stackPane);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        searchButton.setOnAction(e->{
            try {
                searchQuery(stackPane);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        updateButton.setOnAction(e->{
            try {
                updateQuery(stackPane);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        });
        showButton.setOnAction(e->showQuery(stackPane));
    }
}