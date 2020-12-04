package sample.Server;

import sample.Database.ConnectDB;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ServiceSalesObject extends UnicastRemoteObject implements ServiceSales {
    protected ServiceSalesObject() throws RemoteException {
        super();
    }

    Connection conn = ConnectDB.getConnection();
    Statement stmt = null;

    @Override
    public boolean checkRoute(String departure, String destination, String time) throws RemoteException {
        try {
            stmt = conn.createStatement();
            String sql1 = "SELECT ID_Route FROM route WHERE Departure = '" + departure +
                    "' and Destination ='" + destination + "' and Time ='" + time + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public int Route(String departure, String destination, String time) throws RemoteException {
        try {
            stmt = conn.createStatement();
            String sql1 = "SELECT ID_Route FROM route WHERE Departure = '" + departure +
                    "' and Destination ='" + destination + "' and Time ='" + time + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                return rs1.getInt("ID_Route");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getPrice(String departure, String destination, String time) throws RemoteException {
        try {
            stmt = conn.createStatement();
            String sql1 = "SELECT ID_Route FROM route WHERE Departure = '" + departure +
                    "' and Destination ='" + destination + "' and Time ='" + time + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                int ID_Route = rs1.getInt("ID_Route");
                String sql2 = "SELECT Price FROM train WHERE ID_Route = '" + ID_Route +"'";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while (rs2.next()){
                    return rs2.getInt("Price");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Integer> getSeats(String departure, String destination, String time, LocalDate date) throws RemoteException {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            int ID_Route = 0;
            stmt = conn.createStatement();
            String sql1 = "SELECT ID_Route FROM route WHERE Departure = '" + departure +
                    "' and Destination ='" + destination + "' and Time ='" + time + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()){
                ID_Route = rs1.getInt("ID_Route");
            }
            // show data
            String sql2 = "SELECT ID_Seat FROM ticket WHERE ID_Train = '" + ID_Route +
                    "' and Date ='" + date + "'";
            System.out.println(sql2);
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                result.add(rs2.getInt("ID_Seat"));

            }
            // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("cuoi "+ result);
        return result;
    }

    @Override
    public boolean register(String name, String cart, String phone, String pass) throws RemoteException {
        try {
            stmt = conn.createStatement();
            String sql1 = "INSERT INTO user(Name_User, Cart_User, Phone_User, Pass_User) VALUES ('"+name+"' ,'"+ cart+"' ,'"+ phone+"','"+ pass+"')";
            int rs1 = stmt.executeUpdate(sql1);
            while (rs1 == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkLogin(String phone, String pass) throws RemoteException {
        try {
            stmt = conn.createStatement();
            String sql1 = "SELECT * FROM user WHERE Phone_User = '" + phone +
                    "' and Pass_User ='" + pass + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()){
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public int getID_User(String phone, String pass) throws RemoteException {
        int ID_User = 0;
        try {
            stmt = conn.createStatement();
            String sql1 = "SELECT * FROM user WHERE Phone_User = '" + phone +
                    "' and Pass_User ='" + pass + "'";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()){
                ID_User = rs1.getInt("ID_User");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ID_User;
    }


}
