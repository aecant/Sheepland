package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

public abstract class MovimentoAnimale implements Evento {

	private static final long serialVersionUID = 3263700493723371773L;
	private int origine, destinazione;

	public MovimentoAnimale(int origine, int destinazione) {
		this.origine = origine;
		this.destinazione = destinazione;
	}

	public int getOrigine() {
		return origine;
	}

	public int getDestinazione() {
		return destinazione;
	}

	public abstract void aggiornaDati();

	public abstract void visualizza();

}
