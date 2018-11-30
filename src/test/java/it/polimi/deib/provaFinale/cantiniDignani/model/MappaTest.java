package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

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

		s1 = new Strada(t4, t7);
		s6 = new Strada(t4, t5);
		s2 = new Strada(t5, t7);
		s5 = new Strada(t5, t6);
		s4 = new Strada(t6, t7);
	}

	@Test
	public void testTransizione() {
		assertEquals(Mappa.getMappa().transizione(t4, 1), t7);
		assertEquals(Mappa.getMappa().transizione(t4, 6), t5);
		assertEquals(Mappa.getMappa().transizione(t5, 2), t7);
		assertEquals(Mappa.getMappa().transizione(t7, 4), t6);
		assertEquals(Mappa.getMappa().transizione(t6, 4), t7);
	}
	
	@Test
	public void testGetTerritori() {
		assertEquals(Mappa.getMappa().getTerritori().length, CostantiModel.NUM_TERRITORI);
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
		assertFalse(Mappa.getMappa().sonoContigue(s1, s1));
		assertFalse(Mappa.getMappa().sonoContigue(s2, s2));
	}

	@Test
	public void testSonoConfinanti() {
		assertTrue(Mappa.getMappa().sonoConfinanti(t4, t5));
		assertTrue(Mappa.getMappa().sonoConfinanti(t4, t7));
		assertFalse(Mappa.getMappa().sonoConfinanti(t4, t6));
	}
	
	@Test
	public void testCodiceStrade() {
		for(int i = 0; i < Mappa.getMappa().getStrade().length; i++) {
			assertTrue(i == Mappa.getMappa().getStrade()[i].getCodice());
		}
	}
	
	@Test
	public void testGetDado() {
		assertEquals(Mappa.getMappa().getDado(s1), 1);
		assertEquals(Mappa.getMappa().getDado(s2), 2);
		assertEquals(Mappa.getMappa().getDado(s4), 4);
		assertEquals(Mappa.getMappa().getDado(s5), 5);
		assertEquals(Mappa.getMappa().getDado(s6), 6);
	}
}
