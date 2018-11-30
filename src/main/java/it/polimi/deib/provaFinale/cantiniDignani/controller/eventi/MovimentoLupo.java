package it.polimi.deib.provaFinale.cantiniDignani.controller.eventi;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;

public class MovimentoLupo extends MovimentoAnimale {

	private static final long serialVersionUID = -2442027714276181519L;
	
	public MovimentoLupo(int origine, int destinazione, DatiTerritorio[] terrDaAggiornare) {
		super(origine, destinazione, terrDaAggiornare);
	}

	@Override
	public void visualizza() {
		MainClient.getUI().movimentoLupo(origine, destinazione);
	}

	@Override
	public String toString() {
		return "Movimento lupo da " + origine + " a " + destinazione;
	}
}
