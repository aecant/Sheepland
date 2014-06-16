package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	void registraGiocatore(String giocatore) throws RemoteException, NomeGiaPresenteException;

	void aggiungiAscoltatore(String giocatore, AscoltatoreEventiRmi ascoltatore) throws RemoteException;

	void riceviMossa(int mossa) throws RemoteException;

}
