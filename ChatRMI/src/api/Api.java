/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author moro
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Api extends Remote {
    public Data incrementCounter(Data value) throws RemoteException;
}