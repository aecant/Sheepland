package model;

import static org.junit.Assert.*;
import model.Agnello;
import model.Gregge;
import model.Mappa;
import model.Pecora;

import org.junit.Before;
import org.junit.Test;

public class GreggeTest {
	Gregge gregge1, gregge2, gregge3;
	Pecora pecora;
	Agnello agnello;
	
	@Before
	public void setUp() {
		pecora = new Pecora(Mappa.getMappa().getTerritori()[0], false);
		agnello = new Agnello(Mappa.getMappa().getTerritori()[0], false);
		gregge1 = new Gregge();
		gregge2 = new Gregge();
		gregge3 = new Gregge();
		
		gregge1.aggiungi(pecora);
		gregge1.rimuovi(pecora);
		
		gregge2.aggiungi(pecora);
		
		gregge3.aggiungi(agnello);
	}
	
	@Test
	public void testAggiungiRimuovi() {
		assertFalse(gregge1.getPecore().contains(pecora));
		assertTrue(gregge2.getPecore().contains(pecora));
	}
	
	
	@Test
	public void testTrasformaAgnelloInPecora() {
		assertTrue(gregge3.getPecore().contains(agnello));
		assertTrue(gregge3.getPecore().get(0) instanceof Agnello);
		gregge3.trasformaAgnelloInPecora(agnello);
		assertTrue(gregge3.getPecore().get(0) instanceof Pecora);
				
	}
}
