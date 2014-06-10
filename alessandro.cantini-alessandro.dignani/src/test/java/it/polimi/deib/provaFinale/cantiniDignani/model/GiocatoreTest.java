package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore.DenaroInsufficienteException;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	Integer denaro = 100;
	String nome = "esempio";
	Giocatore giocatore;

	@Before
	public void setUp() {
		giocatore = new Giocatore(nome);
	}

	@Test
	public void testGiocatore() {
		assertEquals(giocatore.getNome(), nome);
		assertEquals(giocatore.getDenaro(), Integer.valueOf(0));
	}

	@Test
	public void testSottraiDenaro() {
		giocatore.aggiungiDenaro(denaro);
		assertEquals(giocatore.getDenaro(), denaro);
		giocatore.sottraiDenaro(denaro);
		assertEquals(giocatore.getDenaro(), Integer.valueOf(0));
	}

	@Test(expected = DenaroInsufficienteException.class)
	public void testDenaroInsufficienteException() {
		giocatore.aggiungiDenaro(denaro);
		giocatore.sottraiDenaro(denaro / 2);
		giocatore.sottraiDenaro(denaro);
	}

}
