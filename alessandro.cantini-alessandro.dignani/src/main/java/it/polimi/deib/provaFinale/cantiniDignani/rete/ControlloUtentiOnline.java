package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.GiocatoreDisconnesso;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

import java.util.List;

public class ControlloUtentiOnline extends Thread {

	private final List<Utente> utenti;
	private final List<GestorePartita> gestori;
	private boolean on;

	public ControlloUtentiOnline(List<Utente> utenti, List<GestorePartita> gestori) {
		this.utenti = utenti;
		this.gestori = gestori;
		on = true;
	}

	@Override
	public void run() {
		while (on) {
			for (Utente utente : utenti) {
				if (!utente.isOnline()) {
					utente.getCodaMosse().aggiungi(CostantiRete.MOSSA_DISCONNESSIONE);
					utente.setConnessione(null);
					GestorePartita gestore = getGestore(utente);
					try {
						gestore.wait();
						for(Utente utentePartita : gestore.getUtenti()) {
							utentePartita.inviaEvento(new GiocatoreDisconnesso(utente.getNome()));
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// TODO continuare qua: interrompe il gestore e invia a
					// tutti l'evento di disconnessione

				}
			}
		}
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ferma() {
		on = false;
	}

	private GestorePartita getGestore(Utente utente) {
		for (GestorePartita gestore : gestori) {
			if (gestore.getUtenti().contains(utente)) {
				return gestore;
			}
		}
		throw new IllegalArgumentException(utente + " non trovato");
	}

}
