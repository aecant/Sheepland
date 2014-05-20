package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller.server.Sorte;

import org.junit.Before;
import org.junit.Test;

public class SorteTest {
	private final int NUMERO_LANCI = 1000000;
	private final int DELTA = NUMERO_LANCI / 50;
	Integer contTrue, contFalse;
	Integer[] contDado;

	@Before
	public void setUpMoneta() {
		contTrue = contFalse = 0;
		for (int i = 0; i < NUMERO_LANCI; i++) {
			if (Sorte.lanciaMoneta())
				contTrue++;
			else
				contFalse++;
		}
	}

	@Before
	public void setUpDado() {
		contDado = new Integer[6];
		for(int i = 0; i < contDado.length; i++)
			contDado[i] = new Integer(0);
		
		for (int i = 0; i < NUMERO_LANCI; i++)
			contDado[Sorte.lanciaDado() - 1]++;
	}

	/**
	 * Controlla che il lancio del dado dia valori compresi dall'1 al 6
	 */
	@Test
	public void testValoriDado() {
		for (int i = 0; i < NUMERO_LANCI; i++) {
			int lancio = Sorte.lanciaDado();
			assertTrue(lancio >= 1 && lancio <= 6);
		}		
	}
	
	@Test 
	public void testEquitaDado(){
		for(Integer cont : contDado)
			assertEquals(cont, NUMERO_LANCI / 6, DELTA);
	}
	
	@Test
	public void testEquitaMoneta() {
		assertEquals(contTrue, contFalse, DELTA);
	}

}
