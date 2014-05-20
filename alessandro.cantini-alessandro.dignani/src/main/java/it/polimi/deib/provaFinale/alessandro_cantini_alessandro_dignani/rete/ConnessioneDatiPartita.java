package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnessioneDatiPartita extends Remote{
	Tessera[] getTessereConfinanti(int strada) throws RemoteException;
	

}
