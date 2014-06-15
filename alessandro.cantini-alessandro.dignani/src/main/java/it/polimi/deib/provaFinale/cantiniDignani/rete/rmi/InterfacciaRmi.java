package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	void registraGiocatore(String giocatore) throws RemoteException;

	void aggiungiAscoltatore(String giocatore, AscoltatoreRemoto ascoltatore) throws RemoteException;

	void riceviMossa(int mossa) throws RemoteException;

	DatiPartita scaricaDatiPartita(String giocatore) throws RemoteException;

}
