package model;

import static org.junit.Assert.*;
import model.Animale;
import model.Mappa;
import model.Territorio;

import org.junit.Before;
import org.junit.Test;

public class AnimaleTest {
	Territorio t1, t2;
	Animale a;
	
	
	
	@Before
	public void setUp() {
		t1 = Mappa.getMappa().getTerritori()[0];
		t2 = Mappa.getMappa().getTerritori()[1];
		
		a = new Animale(t1);
	}
	
	@Test
	public void testMuoviIn() {
		assertTrue(a.getPosizione().equals(t1));
		a.muoviIn(t2);
		assertTrue(a.getPosizione().equals(t2));
	}
	
	
}
