package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class InterfacciaRmiImpl implements InterfacciaRmi {
	
	private final static Logger LOGGER = Logger.getLogger(InterfacciaRmiImpl.class.getName());
	
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
		LOGGER.info("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(String nome, int mossa)  throws RemoteException{
		server.getUtente(nome).getCodaMosse().aggiungi(mossa);
		LOGGER.info("Mossa ricevuta da " + nome + " : " + mossa);
	}

}
