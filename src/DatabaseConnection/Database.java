package DatabaseConnection;

import java.sql.*;

public class Database {

    private static final String url = "jdbc:postgresql://localhost:5434/user";
    private static final String username = "postgres";
    private static final String password = "aldebaran1711";


    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connection connected successfully!");
            }
        } catch (SQLException ie) {
            ie.printStackTrace();
            System.out.println("Can not connect to database");
        } catch (ClassNotFoundException ie) {
            ie.printStackTrace();
            System.out.println("Can not found class!");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection successfully closed!");
                }
            } catch (SQLException ie) {
                ie.printStackTrace();
                System.out.println("An error occurred when closing database");
            }
        }
    }



}
