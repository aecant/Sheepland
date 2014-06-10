package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRMI extends Remote {

	public void registraGiocatore(String giocatore) throws RemoteException;

	public void aggiungiAscoltatore(String giocatore, AscoltatoreRemoto ascoltatore) throws RemoteException;
	
	public void riceviMossa(Mossa mossa) throws RemoteException;
}
