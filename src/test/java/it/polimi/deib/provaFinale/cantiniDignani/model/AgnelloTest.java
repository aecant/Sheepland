package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import org.junit.Before;
import org.junit.Test;

public class AgnelloTest {
	Agnello a1, a2, a3, a4, a5;
	Territorio t1, t2;
	Pecora p;

	@Before
	public void setUp() {
		t1 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t2 = new Territorio(1, TipoTerritorio.MONTAGNA);
		a1 = new Agnello(t1, false);
		a2 = new Agnello(t2, true);
		a3 = new Agnello(t2, true);
		a4 = new Agnello(t2, false);
		a5 = new Agnello(t1, true);
		p = new Pecora(t1, true);

	}

	/**
	 * Test del costruttore dei Agnello
	 */
	@Test
	public void testAgnello() {
		assertEquals(a4.getEta(), 0);
		assertEquals(a4.getPosizione(), t2);
		assertEquals(a4.isMaschio(), false);
	}

	/**
	 * Invecchia un agnello fino all'ete' massimo, a ogni ciclo verifica che
	 * l'eta' dell'agnello sia uguale all'indice del ciclo
	 */
	@Test
	public void testInvecchia() {
		for (int i = 0; i < CostantiModel.ETA_MAX_AGNELLO; i++) {
			assertEquals(a1.getEta(), i);
			a1.invecchia();
		}
	}
	
	@Test
	public void testEquals() {
		assertFalse(a3.equals(a4));
		assertFalse(a3.equals(a5));
		assertFalse(a4.equals(a5));
		assertFalse(a5.equals(p));
		assertEquals(a2, a3);
		a2.invecchia();
		assertFalse(a2.equals(a3));
		a3.invecchia();
		assertEquals(a2, a3);
	}
}
