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

public interface IMessage extends Remote {
    public void sendMessageUser(int userId, String msg) throws RemoteException;
    public ArrayList<String> getMessages(long timestamp) throws RemoteException;
}


