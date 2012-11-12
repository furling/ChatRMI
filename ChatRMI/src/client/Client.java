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

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;

    public static void main(String[] args) throws Exception {
        System.out.println("Start Client");
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
        for (int i = 1; i <= 100; i++) {
            System.out.println("counter = " +
                remoteApi.incrementCounter(new Data(1)).getValue());
            Thread.sleep(100);
        }
    }
}
