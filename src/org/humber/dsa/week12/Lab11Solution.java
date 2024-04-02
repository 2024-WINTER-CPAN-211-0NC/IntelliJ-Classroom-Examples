package org.humber.dsa.week12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Lab11Solution {

    private static boolean insertDataFromFile(String tableName, String fileName) {
        try {
            Connection connection = ConnectionManager.getConnection();
            //TODO: WRITE YOUR CODE HERE
            //remember to close resources
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void displayTableData(String tableName) {
        try {
            Connection connection = ConnectionManager.getConnection();
            //TODO: WRITE YOUR CODE HERE
            //remember to close resources
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DO NOT CHANGE BELOW LINES OF CODE
     * <p>
     * YOU DON'T HAVE TO EXPLAIN THE BELOW
     * LINES OF CODE IN YOUR VIDEO RECORDING.
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            // Establish a connection
            int option;
            do {
                System.out.print("SELECT A VALID DATABASE FOR THE LAB:\n1. Oracle\n2. MySQL\nEnter 1 or 2: ");
                option = scanner.nextInt();
            } while (option != 1 && option != 2);
            ConnectionManager.choiceOfDatabase = option == 1 ? Database.ORACLE : Database.MYSQL;

            System.out.print("Enter your " + ConnectionManager.choiceOfDatabase.name() + " username: ");
            ConnectionManager.username = scanner.next();
            System.out.print("Enter your " + ConnectionManager.choiceOfDatabase.name() + " password: ");
            ConnectionManager.password = scanner.next();

            if (ConnectionManager.choiceOfDatabase == Database.MYSQL) {
                createDatabaseForMySQL();
            }
            String tableName = "EMPLOYEE_TABLE_BY_" + ConnectionManager.username;
            dropTableIfExists(tableName);
            createTable(tableName);

            //METHOD CALLS FOR LAB
            boolean isSuccessfullyInserted = insertDataFromFile(tableName, "EMPLOYEES.CSV");
            if (isSuccessfullyInserted) {
                System.out.println("Successful Inserted data to Database\nNow Reading from table");
                displayTableData(tableName);
            } else {
                System.out.println("Failed to Insert data");
            }

            //clean up the mess in Humber Calvin
            if (ConnectionManager.choiceOfDatabase == Database.ORACLE) {
                dropTableIfExists(tableName);
            }

            //closing resources
            ConnectionManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createDatabaseForMySQL() throws SQLException {
        executeQuery("CREATE DATABASE IF NOT EXISTS DS_ALGO");
    }

    private static void createTable(String tableName) throws SQLException {
        executeQuery("CREATE TABLE " + tableName + " (\n" +
                "    employee_id INT PRIMARY KEY,\n" +
                "    first_name VARCHAR(50),\n" +
                "    last_name VARCHAR(50),\n" +
                "    email VARCHAR(100),\n" +
                "    department VARCHAR(50)\n" +
                ")\n"
        );
    }

    private static void dropTableIfExists(String tableName) throws SQLException {
        // Dropping the table if it exists
        executeQuery("DROP TABLE " + tableName, false);
    }

    private static void executeQuery(String query) throws SQLException {
        executeQuery(query, true);
    }

    private static void executeQuery(String query, boolean enableErrorLogs) throws SQLException {
        Statement statement = ConnectionManager.getConnection().createStatement();
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            if (enableErrorLogs) {
                System.err.println("Error executing query = " + query);
                e.printStackTrace();
            }
        }
        statement.close();
    }

    private enum Database {
        ORACLE("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "oracle.jdbc.driver.OracleDriver"),
        MYSQL("jdbc:mysql://localhost:8889/DS_ALGO", "com.mysql.cj.jdbc.Driver");

        private final String jdbcUrl;
        private final String jdbcDriver;

        Database(String jdbcUrl, String jdbcDriver) {
            this.jdbcUrl = jdbcUrl;
            this.jdbcDriver = jdbcDriver;
        }
    }

    private static class ConnectionManager {
        private static Database choiceOfDatabase;
        private static Connection connection;
        private static String username;
        private static String password;

        private static Connection getConnection() {
            try {
                if (connection == null || connection.isClosed()) {
                    // Load the Oracle JDBC driver
                    Class.forName(choiceOfDatabase.jdbcDriver);

                    // Establish a connection
                    connection = DriverManager.getConnection(choiceOfDatabase.jdbcUrl, username, password);
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Failed to create a connection");
            }
            return connection;
        }

        private static void closeConnection() throws SQLException {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
}
