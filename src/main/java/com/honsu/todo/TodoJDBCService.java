package com.honsu.todo;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class TodoJDBCService {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/ToDo";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    private static List<Todo> todoList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            retrieveTodo(1); // OK
            System.out.println();
            getTodos("Honsu"); // OK
            System.out.println();
            addTodo("Honsu", "Make coffee", new Date(), false); // not OK
            System.out.println();
            getTodos("Honsu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public static Todo retrieveTodo(int id) throws SQLException{

        Todo todo;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM todo WHERE id=?";

        try {

            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                todo = new Todo(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("desc"),
                        rs.getDate("date"),
                        rs.getBoolean("isDone"));
                System.out.println(todo);  //just for test
                return todo;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return null;
    }

    public static List<Todo> getTodos(String user) throws SQLException{

        List<Todo> todos = new ArrayList<>();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM todo WHERE user_name=?";

        try {

            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, user);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                todos.add(new Todo(rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("desc"),
                        rs.getDate("date"),
                        rs.getBoolean("isDone")));
            }
            System.out.println(todos);  //just for test
            return todos;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return null;
    }

    public static void addTodo(String user_name, String desc, Date date, boolean isDone) throws SQLException{

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO todo (user_name,desc,date,isDone) VALUES (?,?,?,?)";

        try {

            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, desc);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setBoolean(4, isDone);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
