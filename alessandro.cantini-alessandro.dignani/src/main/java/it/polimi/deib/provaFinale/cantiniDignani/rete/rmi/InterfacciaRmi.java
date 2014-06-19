package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	void registraGiocatore(String giocatore, String password) throws RemoteException, NomeGiaPresenteException;

	void aggiungiAscoltatore(String giocatore, InterfacciaAscoltatoreRmi ascoltatore) throws RemoteException;

	void riceviMossa(String giocatore, int mossa) throws RemoteException;

}
