package com.babusa.learn;

import java.sql.*;
import java.io.*;

/*
 * Running in IntelliJ doesn't work
 * down ojdbc jar to local and point classpath to it
 * cmd: export CLASSPATH=.:/tmp/OJDBC-Full/ojdbc6.jar  && javac com/babusa/learn/SQLTest01.java && java com.babusa.learn.SQLTest01
 */

public class SQLTest02 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest02 simpleConnect = new SQLTest02();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        Statement stat = conn.createStatement();

        try (ResultSet result = stat.executeQuery("SELECT * FROM EMPLOYEES WHERE FIRST_NAME = 'James'")) {

            ResultSetMetaData resultSetMetaData= result.getMetaData();

            System.out.println("resultSetMetaData.getColumnCount() :" + resultSetMetaData.getColumnCount());
            System.out.println();
            System.out.println("resultSetMetaData.getColumnLabel(1) :" + resultSetMetaData.getColumnLabel(1));
            System.out.println("resultSetMetaData.getColumnLabel(2) :" + resultSetMetaData.getColumnLabel(2));
            System.out.println("resultSetMetaData.getColumnLabel(3) :" + resultSetMetaData.getColumnLabel(3));

            while (result.next()) {
                System.out.println("");
                System.out.println("> Printing Records...");
                System.out.println("result.getString(1): " + result.getString(1));
                System.out.println("result.getString(\"FIRST_NAME\"): " + result.getString("FIRST_NAME"));
                System.out.println("result.getString(\"LAST_NAME\"): " + result.getString("LAST_NAME"));
                System.out.println("result.getString(\"JOB_ID\"): " + result.getString("JOB_ID"));
                System.out.println("result.getLong(\"Manager_ID\"): " + result.getLong("Manager_ID"));
            }
        }
    }
}
