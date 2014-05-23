package rete;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Tessera;

public interface InterfacciaRMI extends Remote{
	
	public Tessera[] getTessereConfinanti(int codPartita, int strada) throws RemoteException;
	public Integer numero() throws RemoteException;

}
