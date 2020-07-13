package com.babusa.learn;

import java.io.IOException;
import java.sql.*;

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
