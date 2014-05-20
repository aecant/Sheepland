package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Costanti;

import org.junit.Test;

public class CostantiTest {
	
	
	@Test
	public void test() {
		assertEquals(Costanti.MAPPA.length, Costanti.TERRITORIO_CODICE.length);
	}

}
