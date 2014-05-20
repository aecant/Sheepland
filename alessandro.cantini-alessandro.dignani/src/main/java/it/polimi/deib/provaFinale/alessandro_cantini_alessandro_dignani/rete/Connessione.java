package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.rete;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;


public interface Connessione {
	
	DatiTerritorio[] chiediElencoTerritori();
	
	Integer[] chiediStradeConfinanti(Integer strada);
	Integer[] chiediStradeLibere();
	
	DatiTerritorio[] chediPecoreTerritoriVicini(Integer strada);
	
	Pastore[] chiediPastori();
	
	Integer[] chiediRecintiIniziali();
	Integer[] chiediRecintiFinali();
	
	Giocatore[] chiediGiocatoreTurno();
	
	Tessera[] chiediTessereTerritoriConfinanti(Integer strada);
}
