package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

public class GiocatoreInDue extends Giocatore {
	private Pastore secondoPastore;

	public GiocatoreInDue(String nome) {
		super(nome);
	}

	public Pastore getSecondoPastore() {
		return secondoPastore;
	}

	public void setSecondoPastore(Pastore secondoPastore) {
		this.secondoPastore = secondoPastore;
	}

}
