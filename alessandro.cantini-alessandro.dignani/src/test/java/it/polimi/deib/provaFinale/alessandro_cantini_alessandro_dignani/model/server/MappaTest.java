package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server.Utilita;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mappa;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Strada;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Territorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class MappaTest {
	
	Territorio t4, t5, t6, t7;
	Strada s1, s6, s2, s5, s4;

	
	@Before
	public void setUp() {
		t4 = new Territorio(4, TipoTerritorio.DESERTO);
		t5 = new Territorio(5, TipoTerritorio.DESERTO);
		t6 = new Territorio(6, TipoTerritorio.DESERTO);
		t7 = new Territorio(7, TipoTerritorio.LAGO);
		
		s1 = new Strada(t4,t7);
		s6 = new Strada(t4,t5);
		s2 = new Strada(t5,t7);
		s5 = new Strada(t5,t6);
		s4 = new Strada(t6,t7);
	}
	
	
	
	@Test
	public void testDuplicati() {
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getTerritori()));
		assertFalse(Utilita.contieneDuplicati(Mappa.getMappa().getStrade()));
	}

	@Test
	public void testSonoContigue() {
		assertTrue(Mappa.getMappa().sonoContigue(s1, s6));
		assertTrue(Mappa.getMappa().sonoContigue(s6, s1));
		assertTrue(Mappa.getMappa().sonoContigue(s1, s2));
		assertTrue(Mappa.getMappa().sonoContigue(s2, s4));
		assertTrue(Mappa.getMappa().sonoContigue(s4, s5));
		assertTrue(Mappa.getMappa().sonoContigue(s2, s5));

		assertFalse(Mappa.getMappa().sonoContigue(s1, s5));
		assertFalse(Mappa.getMappa().sonoContigue(s5, s1));
		assertFalse(Mappa.getMappa().sonoContigue(s1, s4));
		assertFalse(Mappa.getMappa().sonoContigue(s6, s5));
		
		
	}
	
	@Test
	public void testSonoConfinanti() {
		assertTrue(Mappa.getMappa().sonoConfinanti(t4, t5));
		assertTrue(Mappa.getMappa().sonoConfinanti(t4, t7));
		assertFalse(Mappa.getMappa().sonoConfinanti(t4, t6));
	}
}
