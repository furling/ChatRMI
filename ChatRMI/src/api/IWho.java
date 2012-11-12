/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author moro
 */
public interface IWho extends Remote {
    public ArrayList<String> displayUsers() throws RemoteException;
}