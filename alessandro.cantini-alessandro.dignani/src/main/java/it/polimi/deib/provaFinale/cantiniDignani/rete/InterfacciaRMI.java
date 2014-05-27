package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRMI extends Remote{
	
	public Tessera[] getTessereConfinanti(String giocatore, int strada) throws RemoteException;
	
	public void registraGiocatore(String nome) throws RemoteException;

}
