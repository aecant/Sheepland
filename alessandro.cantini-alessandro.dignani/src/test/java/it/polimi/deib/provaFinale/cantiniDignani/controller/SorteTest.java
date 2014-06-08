package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Sorte;
import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SorteTest {
	private final int NUMERO_LANCI = 1000000;
	private final int DELTA = NUMERO_LANCI / 50;
	Integer contTrue, contFalse;
	Integer[] contDado;
	Territorio terr = new Territorio(0, TipoTerritorio.SHEEPSBURG);

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
		for (int i = 0; i < contDado.length; i++)
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
	public void testPecoraRandom() {
		assertTrue(Sorte.pecoraRandom(terr) instanceof Pecora);
	}

	@Test
	public void testAgnelloRandom() {
		assertTrue(Sorte.pecoraRandom(terr) instanceof Agnello);
	}

	@Test
	@Ignore("Test non deterministico")
	public void testEquitaDado() {
		for (Integer cont : contDado)
			assertEquals(cont, NUMERO_LANCI / 6, DELTA);
	}

	@Test
	@Ignore("Test non deterministico")
	public void testEquitaMoneta() {
		assertEquals(contTrue, contFalse, DELTA);
	}

}
