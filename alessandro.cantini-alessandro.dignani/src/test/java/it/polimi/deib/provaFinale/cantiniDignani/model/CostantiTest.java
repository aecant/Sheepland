package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;

import org.junit.Test;

public class CostantiTest {
	
	
	@Test
	public void test() {
		assertEquals(Costanti.MAPPA.length, Costanti.TERRITORIO_CODICE.length);
		assertEquals(Costanti.NUM_TERRITORI, Mappa.getMappa().getTerritori().length);
		assertEquals(Costanti.NUM_STRADE, Mappa.getMappa().getStrade().length);
	}

}
