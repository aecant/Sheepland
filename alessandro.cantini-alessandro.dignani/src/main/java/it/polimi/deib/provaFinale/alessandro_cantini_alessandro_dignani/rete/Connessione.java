package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Evento;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Mossa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;

public interface Connessione {

	public DatiTerritorio[] chiediElencoTerritori();

	public Integer[] chiediStradeConfinanti(Integer strada);

	public Integer[] chiediStradeLibere();

	public DatiTerritorio[] chediPecoreTerritoriVicini(Integer strada);

	public Pastore[] chiediPastori();

	public Integer[] chiediRecintiIniziali();

	public Integer[] chiediRecintiFinali();

	public Giocatore chiediGiocatoreDiTurno();

	public Tessera[] chiediTessereTerritoriConfinanti(Integer strada);

	public Evento chiediEvento();

	public Mossa[] chiediMosseDisponibili();

	public Tessera[] chiediTessere();

	public Tessera[] chiediTessereInCima();

	public void inviaMossa(Mossa mossaScelta);
}
