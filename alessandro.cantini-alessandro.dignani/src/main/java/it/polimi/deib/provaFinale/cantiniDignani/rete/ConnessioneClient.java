package it.polimi.deib.provaFinale.cantiniDignani.rete;

import it.polimi.deib.provaFinale.cantiniDignani.controller.DatiPartita;

public interface ConnessioneClient {

	void inizializza();

	void registraGiocatore(String nome);

	void inviaMossa(int mossaScelta);

	DatiPartita scaricaDatiPartita();

}
