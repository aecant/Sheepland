package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AgnelloTest {
	Agnello a;
	
	@Before
	public void setUp() {
		a = new Agnello(Mappa.getMappa().getTerritori()[0], false);
	}
	
	@Test
	public void testInvecchia() {
		for(int i = 0; i < Costanti.ETA_MAX_AGNELLO; i++) {
			assertEquals(a.getEta(), i);
			a.invecchia();
		}
	}
}
