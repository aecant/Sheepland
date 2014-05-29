package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Animale;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;

import org.junit.Before;
import org.junit.Test;

public class AnimaleTest {
	Territorio t1, t2;
	Animale a1;

	@Before
	public void setUp() {
		t1 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t2 = new Territorio(5, TipoTerritorio.DESERTO);

		a1 = new Animale(t1);
	}

	/**
	 * Verifica la correttezza dello spostamento
	 */
	@Test
	public void testMuoviIn() {
		assertEquals(a1.getPosizione(), t1);
		a1.muoviIn(t2);
		assertEquals(a1.getPosizione(), t2);
	}

}
