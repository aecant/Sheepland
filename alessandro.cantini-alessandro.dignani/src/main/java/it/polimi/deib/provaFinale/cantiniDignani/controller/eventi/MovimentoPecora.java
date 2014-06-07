package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ClientMain;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoOvino;

public class MovimentoPecora extends Mossa {
	private static final long serialVersionUID = -5815440396346581844L;
	
	private TipoOvino tipo;
	private int origine, destinazione;

	public MovimentoPecora(String giocatore, TipoOvino tipo, int origine, int destinazione) {
		super(giocatore);
		this.tipo = tipo;
		this.origine = origine;
		this.destinazione = destinazione;
	}
	
	public TipoOvino getTipoOvino() {
		return tipo;
	}

	public int getOrigine() {
		return origine;
	}

	public int getDestinazione() {
		return destinazione;
	}

	@Override
	public void aggiornaDati() {
		ClientMain.getDatiPartita().aggiornaTerritori();
	}

	@Override
	public void visualizza() {
		ClientMain.getUI().movimentoPecora(tipo, origine, destinazione);
	}

}
