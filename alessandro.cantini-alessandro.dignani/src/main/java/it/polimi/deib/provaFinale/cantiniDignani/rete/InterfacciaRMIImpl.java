package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.rmi.RemoteException;

public class InterfacciaRMIImpl implements InterfacciaRMI {

	public Tessera[] getTessereConfinanti(String giocatore, int strada) throws RemoteException {
		return Estrattore.tessereConfinanti(ServerMain.getPartita(giocatore), strada);
	}

	public void registraGiocatore(String nome) throws NomeGiaPresenteException {
		if (!ServerMain.aggiungiGiocatore(nome)) {
			throw new NomeGiaPresenteException("Il nome e' gia' registrato");
		}
		System.out.println("Giocatore registrato: " + nome);
	}

}
