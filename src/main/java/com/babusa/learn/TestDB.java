package com.babusa.learn;


import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.Properties;

/*
 * Running in IntelliJ doesn't work
 * down ojdbc jar to local and point classpath to it
 * cmd: export CLASSPATH=.:/tmp/OJDBC-Full/ojdbc6.jar  && javac com/babusa/learn/TestDB.java && java com.babusa.learn.TestDB
 */

public class TestDB {
    public static void main(String args[]) throws IOException {
        try {
            runTest();
        }

        catch (SQLException ex) {
            System.out.println("SQLException");
            for (Throwable t : ex)
                t.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            ex.printStackTrace();
        }
    }

    /**
     * Runs a test by creating a table, adding a value, showing the table contents, and removing
     * the table.
     */
    public static void runTest() throws SQLException, IOException, ClassNotFoundException {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();

            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

            try (ResultSet result = stat.executeQuery("SELECT FIRST_NAME || ' ' ||  LAST_NAME FROM EMPLOYEES")) {
                System.out.println(result.getFetchSize());

                if (result.next())
                    System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }


    /**
     * Gets a connection from the properties specified in the file database.properties.
     * @return the database connection
     */

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        Properties props = new Properties();
        String dbPropPath = "../resources/database.properties";

        try (InputStream in = Files.newInputStream(Paths.get(dbPropPath))) {
            props.load(in);
        }

        String drivers = props.getProperty("jdbc.drivers");

        if (drivers != null) System.setProperty("jdbc.drivers", drivers);

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        Class.forName(drivers);

        return DriverManager.getConnection(url, username, password);
    }
}