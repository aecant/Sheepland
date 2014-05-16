package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Utilita;

import org.junit.Before;
import org.junit.Test;

public class MappaTest {
	
	Territorio t4, t5, t6, t7;
	Strada s1, s2, s3, s4, s5;

	
	@Before
	public void setUp() {
		t4 = new Territorio(4, TipoTerritorio.DESERTO);
		t5 = new Territorio(5, TipoTerritorio.DESERTO);
		t6 = new Territorio(6, TipoTerritorio.DESERTO);
		t7 = new Territorio(7, TipoTerritorio.LAGO);
		
		s1 = new Strada(t4,t7);
		s2 = new Strada(t4,t5);
		s3 = new Strada(t5,t7);
		s4 = new Strada(t5,t6);
		s5 = new Strada(t6,t7);
	}
	
	
	@Test
	public void testDuplicati() {
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getTerritori()));
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getStrade()));
	}

	@Test
	public void testSonoConfinanti() {
		assertTrue(Mappa.getMappa().sonoConfinanti(t4, t5));
	}
}
