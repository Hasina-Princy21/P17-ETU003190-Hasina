package Societe.Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {
    public static Connection getMySQLConnection() {
        String url = "jdbc:mysql://localhost:3306/db_s2_ETU003190";
        String user = "ETU003190";
        String password = "zuEL6WTc"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
