package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaRmi extends Remote {

	void registraGiocatore(String giocatore, String password) throws RemoteException, NomeGiaPresenteException, PasswordSbagliataException;

	void aggiungiAscoltatore(String giocatore, InterfacciaAscoltatoreRmi ascoltatore) throws RemoteException;

	void riceviMossa(String giocatore, int mossa) throws RemoteException;

}
