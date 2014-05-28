package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.RemoteException;

public class InterfacciaRMIImpl implements InterfacciaRMI {

	public Tessera[] getTessereConfinanti(String giocatore, int strada) throws RemoteException {
		return Estrattore.tessereConfinanti(ServerMain.getPartita(giocatore), strada);
	}

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
}
