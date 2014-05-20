package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRMI extends Remote{
	
	public Tessera[] getTessereConfinanti(int codPartita, int strada) throws RemoteException;
	public Integer numero() throws RemoteException;

}
