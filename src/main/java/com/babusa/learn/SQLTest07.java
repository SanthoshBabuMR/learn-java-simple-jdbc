package com.babusa.learn;

import java.io.IOException;
import java.sql.*;


public class SQLTest07 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest07 simpleConnect = new SQLTest07();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        try {
            String TABLE_NAME = "TEMP_BATCH";
            DatabaseMetaData dbm = conn.getMetaData();
            String createTableQuery = "CREATE TABLE " +  TABLE_NAME  + " ( WHAT_IS_YOUR_PURPOSE VARCHAR2(100))";
            String[] insertQueries = new String[2000];
            for(int i = 0; i< insertQueries.length; i++) {
                String data = "To help fellow being " + i;
                insertQueries[i] = "INSERT INTO " +  TABLE_NAME  + " VALUES(' " + data + " ')";
            }

            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();

            // check if "employee" table is there
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null);
            if (!tables.next()) {
                statement.addBatch(createTableQuery);
            }

            for(int i = 0; i< insertQueries.length; i++) {
                statement.addBatch(insertQueries[i]);
            }
            statement.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }

    }
}
