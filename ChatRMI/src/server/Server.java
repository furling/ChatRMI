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
import java.rmi.registry.*;
import java.rmi.server.*;
import api.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Server {
    
    private static final int PORT = 1100;
    private static Registry registry;

    /* RMI Server
     * We can do this somewhere else but hey ... it's just a TP :)
     */
    
    public static void startRegistry() throws RemoteException {
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> " +
            remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    

    public static void main(String[] args) throws Exception {
        startRegistry();
        ServerImpl s = new ServerImpl();
        
        registerObject(IConnect.class.getSimpleName(), s);
        registerObject(IWho.class.getSimpleName(), s);
        registerObject(IBye.class.getSimpleName(), s);
        registerObject(IMessage.class.getSimpleName(), s);
        
        Thread.sleep(5 * 60 * 1000);
    }
}
