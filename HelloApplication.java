package com.example.demodemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 

 *
 * @author Fady Moussa
 * @version Demo
 * @since November 29, 2023
 */

public class HelloApplication extends Application {

//Connecting to MySQL
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "Helloworld420");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Books db1 = new Books();



/**
 *
* This is the start method
* It includes: main scene, add scene, delete scene, checkout scene, checkin scene, display scene
* action listeners.
*

* @param Primarystage- the stage passed into the start application
*
*
 */

    @Override
    public void start(Stage Primarystage) throws IOException {

//______________________________MAIN SCENE______________________________________________________________________

        Label intro_label = new Label("Welcome To LMS");
        intro_label.setId("intro_label");
        Label select_label = new Label("Please select one of the following options");
        select_label.setId("select_label");

        ToggleGroup group2 = new ToggleGroup();
        RadioButton option1 = new RadioButton("INSERT A NEW BOOK");
        option1.setId("option1");
        RadioButton option2 = new RadioButton("DELETE A BOOK");
        option2.setId("option2");
        RadioButton option3 = new RadioButton("DISPLAY ALL BOOKS");
        option3.setId("option3");
        RadioButton option4 = new RadioButton("CHECK OUT A BOOK");
        option4.setId("option4");
        RadioButton option5 = new RadioButton("CHECK IN A BOOK");
        option5.setId("option5");
        option1.setToggleGroup(group2);
        option2.setToggleGroup(group2);
        option3.setToggleGroup(group2);
        option4.setToggleGroup(group2);
        option5.setToggleGroup(group2);

        Button next = new Button("NEXT");
        next.setId("next");

        VBox root = new VBox();
        root.setId("root2");

        Label result_scene = new Label();
        result_scene.setId("result_scene");

        root.getChildren().addAll(intro_label, select_label, option1, option2, option3, option4, option5, next, result_scene);

        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());


        Primarystage.setTitle("Library Management System");
        Primarystage.setScene(scene);
        Primarystage.show();


        //______________________________END OF MAIN SCENE___________________________________________________________________

        //______________________________ADD SCENE___________________________________________________________________________

        Label intro_add = new Label("Please Enter Book Details");
        intro_add.setId("intro_add");



        Label bookTitle_add = new Label("Please Enter Book Title");
        bookTitle_add.setId("bookTitle_add");
        TextField bookTitle = new TextField();
        bookTitle.setId("bookTitle");

        Label bookAuthor_add = new Label("Please Enter Book Author");
        bookAuthor_add.setId("bookAuthor_add");
        TextField bookAuthor = new TextField();
        bookAuthor.setId("bookAuthor");

        Label bookGenre_add = new Label("Please Enter Book Genre");
        bookGenre_add.setId("bookGenre_add");
        TextField bookGenre = new TextField();
        bookGenre.setId("bookGenre");

        Button saveAdd = new Button("ADD BOOK AND EXIT");
        saveAdd.setId("saveADD");

        VBox rootAdd = new VBox();
        rootAdd.setId("rootADD");
        rootAdd.getChildren().addAll(intro_add, bookTitle_add, bookTitle, bookAuthor_add, bookAuthor,
                bookGenre_add, bookGenre, saveAdd);

        Scene addScene = new Scene(rootAdd, 700, 700);
        addScene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());

        //______________________________END OF ADD SCENE____________________________________________________________________


        //_______________________________DELETE SCENE_____________________________________________________________________

        Label intro_delete = new Label("Please Enter Book ID To DELETE!!");
        intro_delete.setId("intro_delete");

        TextField booktoDelete = new TextField();
        booktoDelete.setId("booktoDelete");

        Button saveDelete = new Button("DELETE AND EXIT");
        saveDelete.setId("saveDelete");

        Label result_delete = new Label("");
        result_delete.setId("result_delete");

        VBox rootDelete = new VBox();
        rootDelete.setId("rootDelete");

        rootDelete.getChildren().addAll(intro_delete, booktoDelete, saveDelete, result_delete);

        Scene deleteScene = new Scene(rootDelete, 700, 700);
        deleteScene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());

        //______________________________END OF Delete SCENE_________________________________________________________________


        //______________________________DISPLAY SCENE____________________________________________________________________

        Label intro_Display = new Label("DISPLAYING ALL BOOKS!!");
        intro_Display.setId("intro_Display");

        Label pre_Display = new Label("ID     AUTHOR       TITLE            GENRE" +
                "          STATUS                  DUE_DATE");
        pre_Display.setId("pre_Display");

        Label bo_Label = new Label();
        bo_Label.setId("bo_Label");


        Button exit_Display = new Button("Exit to Menu");
        exit_Display.setId("exit_Display");

        VBox rootDisplay = new VBox();
        rootDisplay.setId("rootDisplay");

        rootDisplay.getChildren().addAll(intro_Display, pre_Display, bo_Label, exit_Display);

        Scene displayScene = new Scene(rootDisplay, 700, 700);
        displayScene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());





        //______________________________END OF DISPLAY SCENE____________________________________________________________________



        //______________________________CHECK OUT SCENE____________________________________________________________________

        Label intro_Checkout =new Label("PLEASE ENTER BOOK ID TO CHECK OUT !!"); intro_Checkout.setId("intro_Checkout");

        TextField booktoCheckout=new TextField();  booktoCheckout.setId("booktoCheckout");

        Button checkout = new Button("Find AND Check-Out") ;  checkout.setId("checkout");

        Label result_Checkout =new Label(); result_Checkout.setId("result_Checkout");
        Label result2_Checkout =new Label(); result2_Checkout.setId("result2_Checkout");

        Button exit_checkout = new Button("Exit to Menu") ;  exit_checkout.setId("exit_checkout");

        VBox rootCheckout = new VBox(); rootCheckout.setId("rootCheckout");

        rootCheckout.getChildren().addAll(intro_Checkout, booktoCheckout, checkout, result_Checkout, result2_Checkout, exit_checkout) ;

        Scene checkoutScene = new Scene (rootCheckout, 700, 700) ;
        checkoutScene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());

        //______________________________END CHECK OUT SCENE____________________________________________________________________

        //______________________________CHECK IN SCENE____________________________________________________________________
        Label intro_Checkin =new Label("PLEASE ENTER BOOK ID TO CHECK IN !!"); intro_Checkin.setId("intro_Checkin");

        TextField booktoCheckin=new TextField();  booktoCheckin.setId("booktoCheckin");

        Button checkin = new Button("Find AND Check-IN") ;  checkin.setId("checkin");

        Label result_Checkin =new Label(); result_Checkin.setId("result_Checkin");
        Label result2_Checkin =new Label(); result2_Checkin.setId("result2_Checkin");

        Button exit_checkin = new Button("Exit to Menu") ;  exit_checkin.setId("exit_checkin");

        VBox rootCheckin = new VBox(); rootCheckin.setId("rootCheckin");

        rootCheckin.getChildren().addAll(intro_Checkin, booktoCheckin, checkin, result_Checkin, result2_Checkin, exit_checkin) ;

        Scene checkinScene = new Scene (rootCheckin, 700, 700) ;
        checkinScene.getStylesheets().add(getClass().getResource("styling.css").toExternalForm());

        //______________________________END OF CHECK IN SCENE_____________________________________________________________


        //_________________________________ACTION LISTENERS__________________________________________________________

        next.setOnAction(e -> {

            if (option1.isSelected()) {
                Primarystage.setScene(addScene);
            }
            if (option2.isSelected()) {
                Primarystage.setScene(deleteScene);
            }
            if (option3.isSelected()) {
                String booksInfo = Books.displayBooks(connection);
                bo_Label.setText(booksInfo);

                Primarystage.setScene(displayScene);
            }
            if (option4.isSelected()) {
                Primarystage.setScene(checkoutScene);
            }
            if (option5.isSelected()) {
                Primarystage.setScene(checkinScene);
            }
        });


        saveAdd.setOnAction(e -> {
            // int id = Integer.parseInt((bookID.getText()));
            String title = bookTitle.getText();
            String author = bookAuthor.getText();
            String genre = bookGenre.getText();

            boolean added = Books.addBook(connection, title, author, genre);

            if (added) {
                result_scene.setText("A New Book Was Added");
                result_scene.setStyle("-fx-text-fill: green;");
                Primarystage.setScene(scene);
            } else {
                result_scene.setText("FAILED TO ADD");
                result_scene.setStyle("-fx-text-fill: red;");
                Primarystage.setScene(scene);
            }

            bookTitle.setText("");
            bookAuthor.setText("");
            bookGenre.setText("");

        });

        saveDelete.setOnAction(e -> {
            int id = Integer.parseInt(booktoDelete.getText());

            boolean deleted = Books.deleteBook(connection, id);

            if (deleted) {
                result_scene.setText("A Book Was Deleted !");
                result_scene.setStyle("-fx-text-fill: green;");
                Primarystage.setScene(scene);
            } else {
                result_delete.setText("Book Was Not Found!!\n" + "Please Try Again");
                result_delete.setStyle("-fx-text-fill: red;");
                booktoDelete.setText("");
            }
        });


        exit_Display.setOnAction(e -> {
            result_scene.setText("ALL BOOKS WERE DISPLAYED !");
            result_scene.setStyle("-fx-text-fill: green;");
            Primarystage.setScene(scene);
        });



        checkout.setOnAction(e -> {
            int idToCheckOut = Integer.parseInt(booktoCheckout.getText());
            CheckOutResult checkOutResult = checkOutBook(connection, idToCheckOut ) ;

            boolean success = checkOutResult.isSuccess();
            LocalDate dueDate = checkOutResult.getDueDate();

            if(success && dueDate != null) {
                result_Checkout.setText("Book with id: "+idToCheckOut+" is successfully checked out");
                result_Checkout.setStyle("-fx-text-fill: green;");
                result2_Checkout.setText("BOOK MUST BE RETURNED BY "+dueDate);
                result2_Checkout.setStyle("-fx-text-fill: green;");

            } else {
                booktoCheckout.setText("");
                result_Checkout.setText("BOOK IS NOT CHECKED OUT");
                result_Checkout.setStyle("-fx-text-fill: red;");
                result2_Checkout.setText("PLEASE TRY AGAIN");
                result2_Checkout.setStyle("-fx-text-fill: red;");
            }

        });



        exit_checkout.setOnAction(e -> {
            result_scene.setText("");
            result_Checkout.setText("");
            result2_Checkout.setText("");
            Primarystage.setScene(scene);
        });


        checkin.setOnAction(e -> {
            int idToCheckIn = Integer.parseInt(booktoCheckin.getText());
            CheckOutResult checkInResult = checkInBook(connection, idToCheckIn) ;

            boolean success2 = checkInResult.isSuccess();
            LocalDate dueDate2 = checkInResult.getDueDate();

            if (success2 && dueDate2 == null) {
                result_Checkin.setText("Book with id: "+idToCheckIn+" is Successfully Checked IN");
                result_Checkin.setStyle("-fx-text-fill: green;");
                result2_Checkin.setText("BOOK DUE DATE is RESET");
                result2_Checkin.setStyle("-fx-text-fill: green;");
            } else {
                booktoCheckin.setText("");
                result_Checkin.setText("BOOK IS NOT CHECKED IN");
                result_Checkin.setStyle("-fx-text-fill: red;");
                result2_Checkin.setText("PLEASE TRY AGAIN");
                result2_Checkin.setStyle("-fx-text-fill: red;");

            }

        });

        exit_checkin.setOnAction(e -> {
            result_scene.setText("");
            result_Checkin.setText("");
            result2_Checkin.setText("");
            Primarystage.setScene(scene);
        });



    }

    @Override
    public void stop() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {



        launch(args);
    }

/**

class for being able to return two results

 */
    public static class CheckOutResult {
        private LocalDate dueDate;
        private boolean success;

        public CheckOutResult(LocalDate dueDate, boolean success) {
            this.dueDate = dueDate;
            this.success = success;
        }
// @return the due date
        public LocalDate getDueDate() {

            return dueDate;
        }
    // @return status
        public boolean isSuccess() {

            return success;
        }
    }
/**
 *
* method for checking out a book
* currentDate the current date
* @param connection MySQL connection
* @param barcode id or barcode of the book
@return due date and checking out status
*
 */
    public static CheckOutResult checkOutBook(Connection connection, int barcode) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = null;
        boolean success = false;

        try {

            PreparedStatement checkOut = connection.prepareStatement("UPDATE books set status = 'checked-out'" +
                    "WHERE id = ? AND due_date IS NULL");

            checkOut.setInt(1, barcode);


            int rowsAffected = checkOut.executeUpdate();

            if (rowsAffected>0){
                success = true ;
                dueDate = currentDate.plusDays(14) ;

                PreparedStatement setDueDate = connection.prepareStatement("UPDATE books set due_date = ? " +
                        "WHERE id = ?");
                setDueDate.setDate(1, Date.valueOf(dueDate));
                setDueDate.setInt(2, barcode);
                setDueDate.executeUpdate();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

return new CheckOutResult(dueDate, success) ;
    }


/**
* method for checking in a book
* @param connection MySQL connection
* @param barcode id or barcode of the book
* @return dueDate and checking out status
*
 */

    public static CheckOutResult checkInBook(Connection connection, int barcode) {


        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.now();
        boolean success = false ;

        try {
            PreparedStatement checkIn = connection.prepareStatement("UPDATE books SET status = 'Checked-In'" +
                    "WHERE id = ? AND due_date IS NOT NULL");

            checkIn.setInt(1, barcode);

            int rowsAffected = checkIn.executeUpdate();

            if (rowsAffected>0) {
                success = true ;
                dueDate = null ;
                PreparedStatement setDueDate = connection.prepareStatement("UPDATE books set due_date = NULL " +
                        "WHERE id = ?");

                setDueDate.setInt(1, barcode);
                setDueDate.executeUpdate();
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new CheckOutResult(dueDate, success) ;

    }















}