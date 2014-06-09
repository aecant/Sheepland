package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;


public class Gui {
	private FinestraChiediNome fcn;
	/**
	 * Implementazione del metodo che chiede all'utente il nome per connettersi
	 * tramite una nuova finestra
	 * 
	 * @return una String con il nome
	 */
	public String chiediNome() {
		fcn = new FinestraChiediNome(this);
		return fcn.riceviNome();
	}
	
	public void inizioPartita() {
		DatiPartita datiPartita = null;// TODO scarico i dati della partita prima [TOGLIERE IL NULL]
		PartitaView partita = new PartitaView(datiPartita);
	}
	
	
	//Test
	public static void main (String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.chiediNome());
		System.out.println(gui.chiediNome());

		gui.inizioPartita();
	}
}
