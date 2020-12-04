package sample.Server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ControllerServer {
    public void StartButton (ActionEvent event){
        try {
            LocateRegistry.createRegistry(6789);
            Naming.bind("rmi://192.168.58.2:6789//Myservice", new ServiceSalesObject());
            System.out.print("Server ready :) !!!! ");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}
