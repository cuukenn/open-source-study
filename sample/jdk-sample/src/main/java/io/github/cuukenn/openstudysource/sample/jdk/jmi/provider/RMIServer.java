package io.github.cuukenn.openstudysource.sample.jdk.jmi.provider;

import io.github.cuukenn.openstudysource.sample.jdk.jmi.metadata.IHelloService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author changgg
 */
public class RMIServer {
    public static void start() throws RemoteException, AlreadyBoundException {
        IHelloService helloService = new DefaultHelloServiceImpl();
        Registry registry = LocateRegistry.createRegistry(8080);
        registry.bind(IHelloService.class.getName(), helloService);
        System.out.println("RMI Server is running");
    }
}
