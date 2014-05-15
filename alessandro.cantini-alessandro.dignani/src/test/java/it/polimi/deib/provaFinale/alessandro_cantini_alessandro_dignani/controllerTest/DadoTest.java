package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controllerTest;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.Dado;

import org.junit.Test;

public class DadoTest {
	private final int NUMERO_LANCI = 10;
	
	@Test
	public void testDado() {
		for(int i = 0; i < NUMERO_LANCI; i++){
			int lancio = Dado.lancia();
			assertTrue(lancio >= 1 && lancio <= 6);
		}
	}
}
