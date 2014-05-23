package model;

import static org.junit.Assert.*;
import model.Strada;
import model.Territorio;
import model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class StradaTest {
	Strada s1, s2, s3, s4;

	@Before
	public void setUp() {
		Territorio t1 = new Territorio(4, TipoTerritorio.DESERTO);
		Territorio t2 = new Territorio(5, TipoTerritorio.DESERTO);
		Territorio t3 = new Territorio(7, TipoTerritorio.LAGO);

		s1 = new Strada(t1, t2);
		s2 = new Strada(t2, t1);
		s3 = new Strada(t1, t3);
		s4 = new Strada(t1, t2);
	}

	@Test
	public void testEquals() {
		assertEquals(s1, s1);
		assertEquals(s1, s4);
		assertEquals(s1, s2);
		assertFalse(s1.equals(s3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStrada() {
		new Strada(null, new Territorio(4, TipoTerritorio.DESERTO));
	}
}
