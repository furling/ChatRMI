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


public class ServerImpl extends UnicastRemoteObject implements IConnect, IBye, IWho, IMessage {
    
    public HashMap<Integer, User> users;
    public ArrayList<Message> messages;

    public ServerImpl() throws RemoteException {
        users = new HashMap<Integer, User>();
        messages = new ArrayList<Message>();
    }

    @Override
    public synchronized int connectUser(String name) throws RemoteException {
        if(displayUsers().contains(name)) {
            throw new RemoteException("Invalid name");
        }
        
        User u = new User(name);
        users.put(u.getId(), u);
        
        System.out.println("New user: " + u);
        return u.getId();
    }
    
    @Override
    public synchronized void disconnectUser(int userId) throws RemoteException {
        if(users.containsKey(userId)) {
            System.out.println("Disconnect user: " + users.get(userId));
            users.remove(userId);
        }
    }

    @Override
    public synchronized ArrayList<String> displayUsers() throws RemoteException {
        ArrayList<String> temp = new ArrayList<String>();
        
        System.out.println("Display users");
        for(User u : users.values()) {
            temp.add(u.getName());
        }
        
        return temp;
    }

    @Override
    public synchronized void sendMessageUser(int userId, String msg) throws RemoteException {
        if(!users.containsKey(userId)) {
            throw new RemoteException("Invalid user id.");
        }
        
        System.out.println("Add message from: " + users.get(userId) + "\n>\t" + msg);
        messages.add(new Message(users.get(userId), msg));
    }

    @Override
    public synchronized ArrayList<String> getMessages(long timestamp) throws RemoteException {
        ArrayList<String> temp = new ArrayList<String>();
        
        System.out.println("Get messages since: " + timestamp);
        for(Message m : messages) {
            if(m.getTimestamp() >= timestamp) {
                temp.add(m.toString());
            }
        }
        
        return temp;
    }
}
