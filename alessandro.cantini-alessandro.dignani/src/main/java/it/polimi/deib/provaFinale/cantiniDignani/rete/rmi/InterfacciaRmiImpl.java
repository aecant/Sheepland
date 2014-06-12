package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.GestoreCoda;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Mossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;

import java.io.PrintStream;
import java.rmi.RemoteException;

public class InterfacciaRmiImpl implements InterfacciaRmi {

	private final PrintStream logger = ServerMain.LOGGER;
	private final GestoreCoda<Evento> gestoreEventi = ServerMain.getGestoreEventi();

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
		ServerRMI.getAscoltatori().put(nome, ascoltatore);
		logger.println("Ascoltatore aggiunto: " + ascoltatore);
	}

	public void riceviMossa(Mossa mossa) {
		gestoreEventi.aggiungi(mossa);
		logger.println("Mossa ricevuta : " + mossa);
	}

	public DatiPartita scaricaDatiPartita(String giocatore) throws RemoteException {
		logger.println(giocatore + " ha scaricato i dati partita");
		return Estrattore.datiPartita(getPartita(giocatore));
	}

	public DatiTerritorio[] chiediElencoTerritori(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato l'elenco dei territori");
		return Estrattore.datiTerritori(getPartita(giocatore));
	}

	public Giocatore[] chiediGiocatori(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato l'elenco dei giocatori");
		return Estrattore.giocatori(getPartita(giocatore));
	}

	public Integer[] chiediRecintiIniziali(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato l'elenco dei recinti iniziali");
		return Estrattore.recintiIniziali(getPartita(giocatore));
	}

	public Integer[] chiediRecintiFinali(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato l'elenco dei recinti finali");
		return Estrattore.recintiFinali(getPartita(giocatore));
	}

	public String chiediGiocatoreDiTurno(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato il giocatore di turno");
		return Estrattore.giocatoreDiTurno(getPartita(giocatore));
	}

	public Tessera[] chiediTessereInCima(String giocatore) throws RemoteException {
		logger.println(giocatore + "ha aggiornato le tessere in cima al mazzo");
		return Estrattore.tessereInCima(getPartita(giocatore));
	}

}
