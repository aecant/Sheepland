package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StradaTest {
	Strada s1, s2, s3;
	
	@Before	
	public void setUp() {
		Territorio t1 = new Territorio (4, TipoTerritorio.DESERTO);
		Territorio t2 = new Territorio (5, TipoTerritorio.DESERTO);
		Territorio t3 = new Territorio (7, TipoTerritorio.LAGO);
		
		s1 = new Strada(t1, t2);
		s2 = new Strada(t2, t1);
		s3 = new Strada(t1, t3);
	}
	
	@Test
	public void testEquals() {
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(s3));
	}
	@Test (expected=IllegalArgumentException.class)
	public void testStrada() {
		new Strada (null, new Territorio(4, TipoTerritorio.DESERTO));
	}
}
