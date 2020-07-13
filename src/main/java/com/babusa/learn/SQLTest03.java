package com.babusa.learn;

import java.io.IOException;
import java.sql.*;

/*
 * Running in IntelliJ doesn't work
 * down ojdbc jar to local and point classpath to it
 * cmd: export CLASSPATH=.:/tmp/OJDBC-Full/ojdbc6.jar  && javac com/babusa/learn/SQLTest01.java && java com.babusa.learn.SQLTest01
 */

public class SQLTest03 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest03 simpleConnect = new SQLTest03();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        String name = "James";
        String query = "SELECT * FROM EMPLOYEES WHERE FIRST_NAME = ?";
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, name);
        ResultSet result = pstat.executeQuery();
        while(result.next()) {
            System.out.println();
            System.out.println("> printing row...");
            System.out.println();
            System.out.println(String.format("%s, %s", result.getString("last_name"), result.getString("First_name")));
        }
    }
}
