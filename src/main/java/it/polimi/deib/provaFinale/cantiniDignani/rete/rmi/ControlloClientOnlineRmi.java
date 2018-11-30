package it.polimi.deib.provaFinale.cantiniDignani.rete.rmi;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlloClientOnlineRmi extends Thread {

	private static final Logger LOGGER = Logger.getLogger(ControlloClientOnlineRmi.class.getName());
	
	private static final int MILLISECONDI_CONTROLLO = 400;

	private final ConnessioneServerRmi connessione;
	private boolean on;

	public ControlloClientOnlineRmi(ConnessioneServerRmi connessione) {
		this.connessione = connessione;
		on = true;
	}

	@Override
	public void run() {
		while (on) {
			for (String nome : Utilita.copia(connessione.getAscoltatori().keySet())) {
				try {
					connessione.getAscoltatori().get(nome).seiOnline();
				} catch (RemoteException e) {
					LOGGER.log(Level.FINE, "disconnessione giocatore", e);
					connessione.getServerSheepland().gestisciDisconnessione(connessione.getServerSheepland().getUtente(nome));
					break;
				}
			}
			
			try {
				Thread.sleep(MILLISECONDI_CONTROLLO);
			} catch (InterruptedException e) {
				LOGGER.log(Level.SEVERE, "Interruzione anomala", e);
			}
		}
	}
	
	public void ferma() {
		on = false;
	}

}
