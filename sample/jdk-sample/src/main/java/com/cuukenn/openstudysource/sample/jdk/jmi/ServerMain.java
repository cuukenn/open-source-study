package com.cuukenn.openstudysource.sample.jdk.jmi;

import com.cuukenn.openstudysource.sample.jdk.jmi.provider.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * @author changgg
 */
public class ServerMain {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        RMIServer.start();
    }
}
