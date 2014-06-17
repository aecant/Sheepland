package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;

import java.util.List;

public class ControlloUtentiOnline extends Thread{

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
		while(on) {
			for(Utente ut : utenti) {
				if(!ut.isOnline()) {
					
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

}
