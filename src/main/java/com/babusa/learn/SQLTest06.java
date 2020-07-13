package com.babusa.learn;

import java.io.IOException;
import java.sql.*;


public class SQLTest06 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest06 simpleConnect = new SQLTest06();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        DatabaseMetaData meta = conn.getMetaData();
        String out = String.format("\nmeta.supportsBatchUpdates(): %s", meta.supportsBatchUpdates());
        out += String.format("\nmeta.supportsGroupBy(): %s", meta.supportsGroupBy());
        System.out.println(out);
    }
}
