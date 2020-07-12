package com.babusa.learn;

import java.sql.*;
import java.io.*;

/*
 * Running in IntelliJ doesn't work
 * down ojdbc jar to local and point classpath to it
 * cmd: export CLASSPATH=.:/tmp/OJDBC-Full/ojdbc6.jar  && javac com/babusa/learn/SQLTest01.java && java com.babusa.learn.SQLTest01
 */

public class SQLTest01 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest01 simpleConnect = new SQLTest01();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        Statement stat = conn.createStatement();

        try {
            stat.executeUpdate("DROP TABLE Greetings");
        } catch (SQLSyntaxErrorException e) {
//            e.printStackTrace();
//            System.out.println("Ignore exception: table doesn't exists");
//            System.out.println("Proceeding ...");
//            System.out.println(""   );e.printStackTrace();
//            System.out.println("Ignore exception: table doesn't exists");
//            System.out.println("Proceeding ...");
//            System.out.println(""   );
        }

        stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
        stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

        try (ResultSet result = stat.executeQuery("SELECT * From Greetings")) {

            if (result.next())
                System.out.println(result.getString(1));

        }
        stat.executeUpdate("DROP TABLE Greetings");

    }
}