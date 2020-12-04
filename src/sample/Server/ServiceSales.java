package sample.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface ServiceSales extends Remote {
    public boolean checkRoute(String departure, String destination, String time) throws RemoteException;
    public int Route(String departure, String destination, String time) throws RemoteException;
    public int getPrice(String departure, String destination, String time) throws RemoteException;
    public ArrayList<Integer> getSeats(String departure, String destination, String time, LocalDate date) throws RemoteException;
    public boolean register(String name, String cart, String phone, String pass) throws RemoteException;
    public boolean checkLogin(String phone, String pass) throws RemoteException;
    public int getID_User(String phone, String pass) throws RemoteException;
}
