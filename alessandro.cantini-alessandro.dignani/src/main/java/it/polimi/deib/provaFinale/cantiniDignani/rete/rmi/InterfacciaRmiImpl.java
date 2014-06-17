package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.io.PrintStream;
import java.rmi.RemoteException;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	private final PrintStream logger = ServerMain.LOGGER;
	private final ConnessioneServerRmi connessione;
	
	public InterfacciaRmiImpl(ConnessioneServerRmi connessione) {
		this.connessione = connessione;
	}

	public void registraGiocatore(String nome, String password) throws RemoteException, NomeGiaPresenteException {
		if (!ServerMain.aggiungiUtente(nome, password, connessione)) {
			throw new NomeGiaPresenteException();
		}
		logger.println("Giocatore registrato: " + nome);
	}

	public void aggiungiAscoltatore(String nome, AscoltatoreEventiRmi ascoltatore) throws RemoteException {
		connessione.getAscoltatori().put(nome, ascoltatore);
		logger.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(String nome, int mossa) {
		ServerMain.getUtente(nome).getCodaMosse().aggiungi(mossa);

		logger.println("Mossa ricevuta da " + nome + " : " + mossa);
	}

}
