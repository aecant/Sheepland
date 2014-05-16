package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Utilita;

import org.junit.Test;

public class MappaTest {
	
	@Test
	public void testDuplicati() {
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getTerritori()));
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getStrade()));
	}

}
