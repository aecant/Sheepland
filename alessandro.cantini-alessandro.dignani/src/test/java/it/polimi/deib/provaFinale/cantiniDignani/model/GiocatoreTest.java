package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore.DenaroInsufficienteException;

import java.util.Map;

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

	@Test
	public void numeroTesserePerTipo() {
		Mazzo mazzo = new Mazzo();
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.BOSCO));
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.BOSCO));
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.BOSCO));
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.CAMPO));
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.CAMPO));
		giocatore.aggiungiTessera(mazzo.prelevaTessera(TipoTerritorio.DESERTO));
		Map<TipoTerritorio, Integer> occorrenze = giocatore.numeroTesserePerTipo();
		assertTrue(occorrenze.get(TipoTerritorio.BOSCO) == 3);
		assertTrue(occorrenze.get(TipoTerritorio.CAMPO) == 2);
		assertTrue(occorrenze.get(TipoTerritorio.DESERTO) == 1);
		assertTrue(occorrenze.get(TipoTerritorio.LAGO) == 0);
		assertTrue(occorrenze.get(TipoTerritorio.MONTAGNA) == 0);
		
		giocatore.rimuoviTessera(TipoTerritorio.BOSCO);
		giocatore.rimuoviTessera(TipoTerritorio.BOSCO);
		giocatore.rimuoviTessera(TipoTerritorio.CAMPO);
		giocatore.rimuoviTessera(TipoTerritorio.CAMPO);
		
		
		
		occorrenze = giocatore.numeroTesserePerTipo();
		assertEquals(occorrenze.get(TipoTerritorio.BOSCO), Integer.valueOf(1));
		assertEquals(occorrenze.get(TipoTerritorio.CAMPO), Integer.valueOf(0));

		
	}
}
