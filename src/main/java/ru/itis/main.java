package ru.itis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "erfan443565";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/homework2";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        viewTable(statement);
        addRow(statement);
        searchById(statement);

    }

    public static void viewTable(Statement statement ) throws Exception{
        ResultSet result = statement.executeQuery("select * from persons");
        while (result.next()){
            System.out.println(result.getInt("id") + " " + result.getString("first_name") + " " + result.getString("last_name") + " " + result.getInt("age"));
        }
    }

    public static void addRow(Statement statement) throws Exception{
        Scanner scanner = new Scanner((System.in));
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        int age = scanner.nextInt();

        String sqlInserUser = "INSERT INTO persons(first_name, last_name, age) " +
                "VALUES('" + firstName + "', '" + lastName + "', '" + age + "');";

        int affectedRows = statement.executeUpdate(sqlInserUser);
        System.out.println("added " + affectedRows + " row");
    }

    public static void searchById(Statement statement) throws Exception{
        System.out.println("Select a id");
        Scanner scanner = new Scanner((System.in));
        int id = scanner.nextInt();

        ResultSet result = statement.executeQuery("select * from persons WHERE id = " + id);
        while (result.next()){
            System.out.println(result.getString("first_name") + " " + result.getString("last_name") + " " + result.getInt("age"));
        }
        }
    }

