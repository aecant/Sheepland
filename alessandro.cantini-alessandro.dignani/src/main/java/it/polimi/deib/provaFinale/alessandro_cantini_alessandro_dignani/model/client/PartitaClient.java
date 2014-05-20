package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.client;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Giocatore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server.Tessera;


public class PartitaClient {
	Territorio[] territori;
	Pastore[] pastori;
	Integer[] recintiIniziali;
	Integer[] recintiFinali;
	Giocatore giocatoreDiTurno;
	Tessera[] tessere;
}
