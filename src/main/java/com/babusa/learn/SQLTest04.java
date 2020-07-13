package com.babusa.learn;

import java.io.IOException;
import java.sql.*;


public class SQLTest04 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest04 simpleConnect = new SQLTest04();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet mrs = meta.getTables(null, null, null, new String[] { "TABLE" });
        while(mrs.next()) {
            String out = String.format("%s %s %s %s",
                    "Schema: ",
                    mrs.getString(2),
                    "; Table: ",
                    mrs.getString(3));
            System.out.println(out);
        }
    }
}
