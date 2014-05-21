package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.view;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Mossa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pecora;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete.DatiPartita;

public interface InterfacciaUtente {

	public Mossa chiediMossa(Mossa[] mosseDisponibili);

	public void movimentoPecora(Pecora pecora, Territorio origine, Territorio destinazione);

	public void movimentoPastore(Pastore pastore, Strada origine, Strada destinazione);

	public void movimentoLupo(Territorio origine, Territorio destinazione);

	public void movimentoPecoraNera(Territorio origine, Territorio destinazione);

	public void lancioDado(Integer numero);

	public void acquistoTessera(Giocatore giocatore, Tessera tessera);

	public void abbattimento(Giocatore giocatore, Pecora pecora);

	public DatiPartita scaricaDatiPartita();

	public void inizioTurno(Giocatore giocatore);

	public void trasformazioneAgnello(boolean maschio, Integer territorio);

}
