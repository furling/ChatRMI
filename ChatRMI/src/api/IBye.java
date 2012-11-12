/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author moro
 */
public interface IBye extends Remote {
    public void disconnectUser(int userId) throws RemoteException;
}