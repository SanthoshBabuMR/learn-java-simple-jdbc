package com.babusa.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractSQLTestRunner {

    public void runTest() {
        try {
            try(Connection connection = SQLConnectUtil.getConnection()) {
                System.out.println("");
                System.out.println("> execute(connection) ...");
                System.out.println("");
                execute(connection);
            };
        }
        catch (IOException ex) {
            System.out.println("IOException");
            ex.printStackTrace();
        }
        catch (SQLException ex) {
            System.out.println("SQLException");
            for (Throwable t : ex)
                t.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
            ex.printStackTrace();
        }
    }

    public abstract void execute(Connection conn) throws SQLException, ClassNotFoundException, IOException;
}
