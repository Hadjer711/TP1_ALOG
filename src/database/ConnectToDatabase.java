package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectToDatabase {

    public static Connection createConnection() {
        String DB_URL = "jdbc:mysql://localhost:3306/rdv";
        String DB_USER = "root";
        String DB_PASS = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

}