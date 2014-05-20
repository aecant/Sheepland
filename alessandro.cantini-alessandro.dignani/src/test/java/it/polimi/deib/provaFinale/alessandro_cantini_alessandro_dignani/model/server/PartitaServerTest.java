package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.ColorePastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Partita;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Pastore;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class PartitaServerTest {
	Partita partita;
	Strada s1, s2, s3;
	Territorio t0, t1, t4, t7;
	
	@Before
	public void setUp() {
		partita = new Partita();
		
		t0 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t1 = new Territorio(1, TipoTerritorio.MONTAGNA);
		t4 = new Territorio(4, TipoTerritorio.DESERTO);
		t7 = new Territorio(7, TipoTerritorio.LAGO);
		
		s1 = new Strada(t0, t1);
		s2 = new Strada(t0, t4);
		s3 = new Strada(t0, t7);
		
		partita.getRecinti().aggiungi(s1);
		partita.aggiungiGiocatore("Esempio");
		partita.getGiocatori().get(0).aggiungiPastore(new Pastore(s2, ColorePastore.BLU));
	}

	@Test
	public void testStradaLibera() {
		assertTrue(partita.stradaLibera(s3));
		assertFalse(partita.stradaLibera(s1));
		assertFalse(partita.stradaLibera(s2));
	}

}
