package com.cuukenn.openstudysource.jdk.jmi.provider;

import com.cuukenn.openstudysource.jdk.jmi.metadata.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author changgg
 */
public class DefaultHelloServiceImpl extends UnicastRemoteObject implements IHelloService {
    protected DefaultHelloServiceImpl() throws RemoteException {
    }

    @Override
    public String getMessage() {
        System.out.println("receive request");
        return "messages-" + ThreadLocalRandom.current().nextInt();
    }
}
