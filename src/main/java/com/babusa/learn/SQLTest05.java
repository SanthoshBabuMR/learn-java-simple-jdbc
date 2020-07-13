package com.babusa.learn;

import java.io.IOException;
import java.sql.*;


public class SQLTest05 extends AbstractSQLTestRunner {

    public static void main(String args[]) throws IOException {
        SQLTest05 simpleConnect = new SQLTest05();
        simpleConnect.runTest();
    }

    @Override
    public void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException {
        Statement stat = conn.createStatement();
        ResultSet mrs = stat.executeQuery("SELECT * FROM employees");
        ResultSetMetaData meta = mrs.getMetaData();
        for(int i=1;i<meta.getColumnCount();i++) {
            String colName = meta.getColumnName(i);
            int colWidth = meta.getColumnDisplaySize(1);
            String out = String.format("%s %s; %s %s;",
                    "colName",
                    colName,
                    "colWidth",
                    colWidth
                    );
            System.out.println(out);
            System.out.println();
        }
    }
}
