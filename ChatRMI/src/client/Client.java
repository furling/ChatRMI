/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author moro
 */
import java.rmi.registry.*;
import api.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1100;
    private static final int REFRESH_INTERVAL = 5000;
    private static Registry registry;

    private static void printHelp() {
        System.out.println("Help:");
        System.out.println("send: msg - Send a message to the server");
        System.out.println("bye - Leave the chat");
        System.out.println("who - Display users on the chat");
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Start Client\n");
        registry = LocateRegistry.getRegistry(HOST, PORT);
        
        IConnect connect = (IConnect)registry.lookup(IConnect.class.getSimpleName());
        IBye bye = (IBye)registry.lookup(IBye.class.getSimpleName());
        IWho who = (IWho)registry.lookup(IWho.class.getSimpleName());
        IMessage message = (IMessage)registry.lookup(IMessage.class.getSimpleName());
        
        int userId = 0;
        String name = "";
        boolean co = true;
        long lastRefresh = System.currentTimeMillis();       
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        

        while(userId == 0) {
            try {
                System.out.println("Taper votre nom:");
                name = clavier.readLine();
                userId = connect.connectUser(name);
            } catch(RemoteException e) {
                System.out.println("Invalid name");
            }
        }
        
        System.out.println("You are login as " + name);
   
        while(co) {
            String cmdraw = clavier.readLine();
            String cmd = cmdraw.toLowerCase();
            
            if(cmd.equals("bye")) {
                bye.disconnectUser(userId);
                System.out.println("You are now logoff! Bye :)");
                co = false;
            } else if(cmd.equals("who")) {
                try {
                    ArrayList<String> users = who.displayUsers();
                    System.out.println("Users:");
                    
                    for(String s : users) {
                        System.out.println("   > " + s);
                    }
                } catch(RemoteException e) {
                    System.out.println("Cannot display users list...");
                }
            } else if(cmd.startsWith("send ")) {
                try {
                    message.sendMessageUser(userId, cmdraw.substring(4));
                } catch(RemoteException e) {
                    System.out.println("Cannot send message...");
                }
            } else if(cmd.equals("help")) {
                printHelp();
            } else {
                System.out.println("Unknow command...");
            }
            
            if(System.currentTimeMillis() - lastRefresh > REFRESH_INTERVAL) {
                try {
                    ArrayList<String> messages = message.getMessages(lastRefresh);
                    lastRefresh = System.currentTimeMillis();
                    
                    System.out.println("Messages:");
                    for(String s: messages) {
                        System.out.println(s);
                    }
                } catch(RemoteException e) {
                    System.out.println("Cannot refresh messages...");
                }
            }
        }
    }
}
