package database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectToDatabase {

    public static Connection createConnection() {
<<<<<<< HEAD
        String dbUrl = "jdbc:mysql://localhost:3306/rdv";
        String DB_USER = "root";
        String DB_PASS = "";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, DB_USER, DB_PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

}