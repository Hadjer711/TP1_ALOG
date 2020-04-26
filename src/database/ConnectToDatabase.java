package database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectToDatabase {

    public static Connection createConnection() {
        String dbUrl = "jdbc:mysql://localhost:3306/rdv";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, "root", "");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

}