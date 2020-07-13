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
            String createTableQuery = "CREATE TABLE " +  TABLE_NAME  + " ( data VARCHAR2(100))";
            String[] insertQueries = new String[10];
            for(int i = 0; i< insertQueries.length; i++) {
                if(i != 7) {
                    String data = "To help fellow being " + i;
                    insertQueries[i] = "INSERT INTO " +  TABLE_NAME  + " VALUES(' " + data + " ')";
                } else {
                    // Inspect the result for 7th position and it would reflect the number of rows effected by this operation
                    insertQueries[i] = "UPDATE " + TABLE_NAME + " set data = 'RESET'";
                }

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
            int[] result = statement.executeBatch();

            conn.commit();
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }

    }
}
