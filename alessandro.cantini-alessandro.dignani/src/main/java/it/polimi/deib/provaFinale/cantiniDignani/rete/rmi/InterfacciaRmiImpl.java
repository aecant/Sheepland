package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.io.PrintStream;
import java.rmi.RemoteException;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	private final PrintStream logger = ServerMain.LOGGER;
	private final GestoreCoda<Integer> gestoreEventi = ServerMain.getGestoreEventi();

	// TODO verificare se si riesce a fare qualcosa di più efficiente
	private Partita getPartita(String nome) {
		return ServerMain.getPartita(nome);
	}

	public void registraGiocatore(String nome) throws NomeGiaPresenteException, RemoteException {
		if (!ServerMain.aggiungiGiocatore(nome)) {
			throw new NomeGiaPresenteException();
		}
		logger.println("Giocatore registrato: " + nome);
	}

	public void aggiungiAscoltatore(String nome, AscoltatoreRemoto ascoltatore) throws RemoteException {
		ServerRmi.getAscoltatori().put(nome, ascoltatore);
		logger.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(int mossa) {
		gestoreEventi.aggiungi(mossa);
		logger.println("Mossa ricevuta : " + mossa);
	}

	public DatiPartita scaricaDatiPartita(String giocatore) throws RemoteException {
		logger.println(giocatore + " ha scaricato i dati partita");
		return Estrattore.datiPartita(getPartita(giocatore));
	}

}
