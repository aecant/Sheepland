package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PecoraTest {
	Pecora p1, p2, p3, p4;
	Territorio t1, t2;
	
	@Before
	public void setUp() {
		t1 = new Territorio(4, TipoTerritorio.DESERTO);
		t2 = new Territorio(5, TipoTerritorio.DESERTO);
		p1 = new Pecora(t1, false);
		p2 = new Pecora(t1, false);
		p3 = new Pecora(t2, true);
		p4 = new Pecora(t1, true);
	}
	
	/**
	 * Test del costruttore e dei getter
	 */
	@Test
	public void testPecora() {
		assertEquals(p1.getPosizione(), t1);
		assertEquals(p3.getPosizione(), t2);
		assertEquals(p1.isMaschio(), false);
		assertEquals(p3.isMaschio(), true);
	}
	
	@Test
	public void testEquals() {
		assertEquals(p1, p2);
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(p4));
		assertFalse(p2.equals(p4));
	}

}
