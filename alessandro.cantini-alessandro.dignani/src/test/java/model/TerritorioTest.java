package model;

import static org.junit.Assert.*;
import model.Territorio;
import model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class TerritorioTest {
	Territorio t1, t2, t3, t4;
		
	@Before
	public void setUp() {
		t1 = new Territorio(4, TipoTerritorio.DESERTO);
		t2 = new Territorio(4, TipoTerritorio.DESERTO);
		t3 = new Territorio(7, TipoTerritorio.LAGO);
		t4 = new Territorio(4, TipoTerritorio.MONTAGNA);
		
	}
	
	@Test
	public void testEquals() {
		assertEquals(t1, t2);
		assertFalse(t1.equals(t3));
	}
	
	@Test (expected = RuntimeException.class)
	public void testEccezione() {
		t1.equals(t4);
	}
}
