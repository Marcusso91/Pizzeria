package feladat;

import java.sql.*;
import java.util.*;

public class DataBaseConnection {

    private static Connection conn = null;

    static {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria", "root", "");

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }
    }

    public static Connection getConnection() {

        return conn;
    }

}
