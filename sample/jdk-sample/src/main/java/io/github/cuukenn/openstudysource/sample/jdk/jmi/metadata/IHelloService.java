package io.github.cuukenn.openstudysource.sample.jdk.jmi.metadata;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author changgg
 */
public interface IHelloService extends Remote {
    /**
     * 获取消息
     *
     * @return String
     * @throws RemoteException 远程执行异常
     */
    String getMessage() throws RemoteException;
}
