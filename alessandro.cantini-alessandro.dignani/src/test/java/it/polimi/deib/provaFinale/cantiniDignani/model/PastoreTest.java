package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class PastoreTest {
	Pastore pastore;
	Territorio t0, t1, t4;
	Strada s1, s2;
	
	
	@Before
	public void setUp() {		
		t0 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t1 = new Territorio(1, TipoTerritorio.MONTAGNA);
		t4 = new Territorio(4, TipoTerritorio.DESERTO);
		
		s1 = new Strada(t0, t1);
		s2 = new Strada(t0, t4);
		
		pastore = new Pastore(s1, ColorePastore.BLU);
	}
	
	@Test
	public void test() {
		assertEquals(pastore.getStrada(), s1);
		pastore.muoviIn(s2);
		assertEquals(pastore.getStrada(), s2);
	}

}
