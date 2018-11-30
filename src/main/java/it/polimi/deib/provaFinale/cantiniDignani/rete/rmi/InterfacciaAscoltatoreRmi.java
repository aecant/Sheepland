package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfacciaAscoltatoreRmi extends Remote {

	/**
	 * Riceve un evento inviato dal server
	 * 
	 * @param evento
	 * @throws RemoteException
	 */
	void riceviEvento(Evento evento) throws RemoteException;

	/**
	 * Serve a verificare che l'utente sia online
	 * 
	 * @return true se il giocatore e' online
	 * @throws RemoteException
	 */
	boolean seiOnline() throws RemoteException;

}
