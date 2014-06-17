package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.io.PrintStream;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	private final PrintStream logger = ServerMain.LOGGER;
	private final Hashtable<String, AscoltatoreEventiRmi> ascoltatori;

	public InterfacciaRmiImpl(Hashtable<String, AscoltatoreEventiRmi> ascoltatori) {
		this.ascoltatori = ascoltatori;
	}

	public void registraGiocatore(String nome, String password) throws RemoteException, NomeGiaPresenteException {
		if (!ServerMain.aggiungiUtente(nome, password)) {
			throw new NomeGiaPresenteException();
		}
		logger.println("Giocatore registrato: " + nome);
	}

	public void aggiungiAscoltatore(String nome, AscoltatoreEventiRmi ascoltatore) throws RemoteException {
		ascoltatori.put(nome, ascoltatore);
		logger.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(String nome, int mossa) {
		ServerMain.getUtente(nome).aggiungiMossa(mossa);

		logger.println("Mossa ricevuta da " + nome + " : " + mossa);
	}

}
