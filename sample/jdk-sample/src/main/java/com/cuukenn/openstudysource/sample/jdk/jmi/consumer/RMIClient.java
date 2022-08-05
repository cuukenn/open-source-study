package com.cuukenn.openstudysource.sample.jdk.jmi.consumer;

import com.cuukenn.openstudysource.sample.jdk.jmi.metadata.IHelloService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author changgg
 */
public class RMIClient {
    public static void start() throws RemoteException, AlreadyBoundException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);
        IHelloService service = (IHelloService) registry.lookup(IHelloService.class.getName());
        System.out.println(service.getMessage());
    }
}
