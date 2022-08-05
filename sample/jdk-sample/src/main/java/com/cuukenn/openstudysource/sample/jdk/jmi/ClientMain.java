package com.cuukenn.openstudysource.sample.jdk.jmi;

import com.cuukenn.openstudysource.sample.jdk.jmi.consumer.RMIClient;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author changgg
 */
public class ClientMain {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException, NotBoundException {
        RMIClient.start();
    }
}
