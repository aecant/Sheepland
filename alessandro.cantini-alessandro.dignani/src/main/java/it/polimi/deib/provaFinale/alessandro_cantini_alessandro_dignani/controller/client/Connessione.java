package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.client;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Tessera;


public interface Connessione {
	
	Territorio[] chiediElencoTerritori();
	
	Integer[] chiediStradeConfinanti(Integer strada);
	Integer[] chiediStradeLibere();
	
	Territorio[] chediPecoreTerritoriVicini(Integer strada);
	
	Pastore[] chiediPastori();
	
	Integer[] chiediRecinti();
	Integer[] chiediRecintiFinale();
	
	Giocatore[] chiediGiocatoreTurno();
	
	Tessera[] chiediTessereTerritoriConfinanti(Integer strada);
}
