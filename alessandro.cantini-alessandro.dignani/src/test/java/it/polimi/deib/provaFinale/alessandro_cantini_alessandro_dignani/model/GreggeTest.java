package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GreggeTest {
	Gregge gregge1;
	Pecora pecora;
	Gregge gregge2;
	
	@Before
	public void setUp() {
		pecora = new Pecora(Mappa.getMappa().getTerritori()[0], false);
		gregge1 = new Gregge();
		gregge2 = new Gregge();
		
		gregge1.aggiungi(pecora);
		gregge1.rimuovi(pecora);
		
		gregge2.aggiungi(pecora);
	}
	
	@Test
	public void testAggiungiRimuovi() {
		assertFalse(gregge1.getPecore().contains(pecora));
		assertTrue(gregge2.getPecore().contains(pecora));
	}
	
	/**
	 * Testo che il gregge non sia modificabile all'esterno
	 */
	@Test
	public void testGetPecore() {
		gregge2.getPecore().remove(pecora);
		assertTrue(gregge2.getPecore().contains(pecora));
	}

}
