package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	public void registraGiocatore(String giocatore) throws RemoteException;

	public void aggiungiAscoltatore(String giocatore, AscoltatoreRemoto ascoltatore) throws RemoteException;
	
	public void riceviMossa(Mossa mossa) throws RemoteException;
}
