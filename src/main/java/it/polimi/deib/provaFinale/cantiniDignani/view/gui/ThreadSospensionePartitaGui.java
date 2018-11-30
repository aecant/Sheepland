package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.view.ThreadSospensionePartita;

public class ThreadSospensionePartitaGui extends ThreadSospensionePartita {

	public ThreadSospensionePartitaGui(String giocatore) {
		super(giocatore);
	}

	@Override
	protected void visualizzaMessaggio(int secondo) {
		Gui.getFinestraPartita().getPanelMessaggi().visualizzaMessaggio("<html>Il giocatore " + giocatore + " si e' disconnesso!<br /> Attendere " + secondo + " secondi</html>", 1000);
	}

}
