package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GreggeTest {
	Gregge g;
	
	@Before
	public void setUp() {
		Pecora pecora = new Pecora(null, false);
		g.inserisci(pecora);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}