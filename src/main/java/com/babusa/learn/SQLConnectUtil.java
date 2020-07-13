package com.babusa.learn;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnectUtil {

    private static String DBPropertiesFile = "/database.properties";

    /**
     * Gets a connection from the properties specified in the file database.properties.
     * @return the database connection
     */
    public static Connection getConnection(InputStream in) throws IOException, ClassNotFoundException, SQLException {

        Properties props = new Properties();
        props.load(in);
        in.close();

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            // 01. JDBC Driver Registeration by storing it properties
            // System.out.println("JDBC Registration through System.setProperty(\"jdbc.drivers\", drivers); ");
            System.setProperty("jdbc.drivers", drivers);
        }


        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        // 02. alternative style of DB registration: Class.forName
        // System.out.println("Class.forName(drivers);");
        // Class.forName(drivers);

        // Log details
        DriverManager.setLogWriter(new PrintWriter(System.out));

        // Get connection
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
        InputStream dbProps = SQLConnectUtil.class.getResourceAsStream(DBPropertiesFile);
        return getConnection(dbProps);
    }

}
