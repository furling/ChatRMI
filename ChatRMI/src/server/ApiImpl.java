/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author moro
 */
import java.rmi.*;
import java.rmi.server.*;
import api.*;

public class ApiImpl extends UnicastRemoteObject implements Api {
    private static final long serialVersionUID = 1L;
    private int counter = 0;

    public ApiImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized Data incrementCounter(Data value) throws RemoteException {
        counter += value.getValue();
        return new Data(counter);
    }
}