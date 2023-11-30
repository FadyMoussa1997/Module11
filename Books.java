
package com.example.demodemo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This is a sample class representing a Books.
 * It stores information about the Book's id, title, author, genre, status, duedate.
 *
 * @author Fady Moussa
 * @version Demo
 * @since November 29, 2023
 */
public class Books {

    private static final String tableName = "books" ;

    private int id ;
    private String title ;
    private String author ;
    private String genre ;
    private String status ;
    private LocalDate dueDate;

/**
 * method for adding books to the Database
 * @param connection connection to database
 * @param title title of the book
 * @param author author of the book
 * @param genre genre of the book
 * @return false if book was not added
 * @return true of book was added

 */
public static boolean addBook (Connection connection, String title, String author, String genre) {

    try{

        PreparedStatement add = connection.prepareStatement("INSERT INTO "+tableName+" (title , author , genre) \n" +
                "VALUES (? , ? , ?)");

        add.setString(1, title);
        add.setString(2, author);
        add.setString(3, genre);
        int rowsaffected = add.executeUpdate();
        return rowsaffected > 0;


    } catch (SQLException e) {
e.printStackTrace();
return false;

    }

}

    /**
     *
     * method for deleting books
     * @param connection connection to Database
     * @param id id of the book
     *
     * @return
     * true if book was successfully deleted
     * false if the book was not successfully deleted
     */
    public static boolean deleteBook (Connection connection, int id) {

    try {
        PreparedStatement delete = connection.prepareStatement("DELETE FROM books WHERE id = ?") ;
        delete.setInt(1, id);
        int rowsaffected = delete.executeUpdate() ;
        return rowsaffected > 0 ;

    } catch (SQLException e) {
        e.printStackTrace();
        return false ;
    }

}

    /**
     *
     * @param connection connection to Database
     * @return toString to display all books
     */
    public static String displayBooks(Connection connection) {
        StringBuilder result = new StringBuilder();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String status = resultSet.getString("status") ;
                String due_date = resultSet.getString("due_date") ;

                // Append book information to the result string
                result.append(" ").append(id).append(",        ").append(title)
                        .append(",        ").append(author).append(",        ").append(genre).append(",         ")
                        .append(status).append(",            ").append(due_date)
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

















}
