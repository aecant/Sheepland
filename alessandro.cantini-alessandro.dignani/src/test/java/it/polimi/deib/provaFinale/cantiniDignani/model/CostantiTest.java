package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;

import org.junit.Test;

public class CostantiTest {
	
	
	@Test
	public void test() {
		assertEquals(CostantiModel.MAPPA.length, CostantiModel.TERRITORIO_CODICE.length);
		assertEquals(CostantiModel.NUM_TERRITORI, Mappa.getMappa().getTerritori().length);
		assertEquals(CostantiModel.NUM_STRADE, Mappa.getMappa().getStrade().length);
	}

}
