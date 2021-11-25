package DAO;

import java.sql.DriverManager;

public class Connection {

    public static java.sql.Connection getConnection() {
        try {
            String server = "localhost";
            String username = "root";
            String password = "";
            String database = "rafalojas";
            String path = "jdbc:mysql://" + server + ":3306/" + database;
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(path, username, password);
            return conn;
        } catch(Exception e) {
            System.err.println(e.getMessage() + "/n/n" + e.getStackTrace());
            return null;
        }
    }

    public static void closeConnection(java.sql.Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch(Exception e) {
                System.err.println(e.getMessage() + "/n/n" + e.getStackTrace());
            }
        }
    }

}
