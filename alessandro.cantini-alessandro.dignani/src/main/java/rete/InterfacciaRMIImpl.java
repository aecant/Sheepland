package rete;

import java.rmi.RemoteException;

import controller.Estrattore;
import controller.ServerMain;
import model.Tessera;

public class InterfacciaRMIImpl implements InterfacciaRMI {

	public Tessera[] getTessereConfinanti(String giocatore, int strada) throws RemoteException {
		return Estrattore.tessereConfinanti(ServerMain.getPartita(giocatore), strada);
	}

	public void registraNome(String nome) throws NomeGiaPresenteException {
		if (!ServerMain.aggiungiGiocatore(nome))
			throw new NomeGiaPresenteException("Il nome e' gia' registrato");
				
	}

	
	
}
