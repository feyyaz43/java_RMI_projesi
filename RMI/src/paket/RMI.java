/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paket;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Feyyaz
 */
public interface RMI extends Remote{
    public byte[] getData(byte[] resim) throws RemoteException;
    public byte[] getData2(byte[] resim) throws RemoteException;
}
