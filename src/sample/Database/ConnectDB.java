package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static Connection getConnection () {
        java.sql.Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_saleticket_networkprogram", "root", "");
            System.out.println("Connect thành công");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connect thất bại");
            e.printStackTrace();
        }

        return connection;
    }

}
