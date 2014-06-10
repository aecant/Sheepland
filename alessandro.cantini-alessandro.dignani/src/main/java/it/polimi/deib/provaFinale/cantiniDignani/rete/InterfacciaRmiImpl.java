package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;

import java.rmi.RemoteException;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	public void registraGiocatore(String nome) throws NomeGiaPresenteException, RemoteException {
		if (!ServerMain.aggiungiGiocatore(nome)) {
			throw new NomeGiaPresenteException();
		}
		System.out.println("Giocatore registrato: " + nome);
	}

	public void aggiungiAscoltatore(String nome, AscoltatoreRemoto ascoltatore) throws RemoteException {
		ServerRMI.getAscoltatori().put(nome, ascoltatore);
		System.out.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(Mossa mossa) {
		ServerMain.getGestoreEventi().aggiungi(mossa);
	}
	
	
}
