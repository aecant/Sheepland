package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;

import java.io.PrintStream;
import java.rmi.RemoteException;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	private final PrintStream logger = ServerSheepland.LOGGER;
	private final ConnessioneServerRmi connessione;
	private final ServerSheepland server;
	
	public InterfacciaRmiImpl(ConnessioneServerRmi connessione, ServerSheepland server) {
		this.connessione = connessione;
		this.server = server;
	}

	public void registraGiocatore(String nome, String password) throws RemoteException, NomeGiaPresenteException, PasswordSbagliataException{
		try {
			server.aggiungiUtente(nome, password, connessione);
		} catch (NomeGiaPresenteException e) {
			throw new NomeGiaPresenteException(e);
		} catch (PasswordSbagliataException e) {
			throw new PasswordSbagliataException(e);
		}
	}

	public void aggiungiAscoltatore(String nome, InterfacciaAscoltatoreRmi ascoltatore) throws RemoteException {
		connessione.getAscoltatori().put(nome, ascoltatore);
		logger.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(String nome, int mossa) {
		server.getUtente(nome).getCodaMosse().aggiungi(mossa);

		logger.println("Mossa ricevuta da " + nome + " : " + mossa);
	}

}
