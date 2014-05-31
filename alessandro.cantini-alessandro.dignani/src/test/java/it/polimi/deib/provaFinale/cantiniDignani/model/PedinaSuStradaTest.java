package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PedinaSuStradaTest {

	PedinaSuStrada p;
	Strada s;
	Territorio t1, t2;

	@Before
	public void setUp() {
		t1 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t2 = new Territorio(5, TipoTerritorio.DESERTO);
		s = new Strada(t1, t2);
		p = new PedinaSuStrada(s);
	}

	@Test
	public void test() {
		assertEquals(s, p.getStrada());
	}

}
