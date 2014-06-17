package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

import it.polimi.deib.provaFinale.cantiniDignani.controller.EstrattoreTest;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.FaseFinale;
import it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita.GestorePartita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import org.junit.Before;
import org.junit.Test;

public class FaseFinaleTest {

	Partita partita;
	FaseFinale fase;
	Territorio t1 = new Territorio(4, TipoTerritorio.DESERTO);
	Territorio t2 = new Territorio(5, TipoTerritorio.DESERTO);
	Territorio t3 = new Territorio(7, TipoTerritorio.LAGO);

	@Before
	public void setUp() {
	
		fase = new FaseFinale(new GestorePartita(EstrattoreTest.listaUtenti(Arrays.asList("esempio1", "esempio2"))));
		
		partita = fase.partita;
		
		partita.getGregge().aggiungi(Sorte.pecoraRandom(t1));
		partita.getGregge().aggiungi(Sorte.pecoraRandom(t2));
		partita.getGregge().aggiungi(Sorte.pecoraRandom(t3));
		
	}

	@Test
	public void testGetValoriTerritori() {
		Map<TipoTerritorio, Integer> valori = fase.valoreTerritori();
		
		assertTrue(valori.get(TipoTerritorio.BOSCO) == 0);
		assertTrue(valori.get(TipoTerritorio.PASCOLO) == 0);
		assertTrue(valori.get(TipoTerritorio.LAGO) == 1);
		assertTrue(valori.get(TipoTerritorio.DESERTO) == 2);
		
		partita.getGregge().getPecoraNera().muoviIn(t1);
		
		valori = fase.valoreTerritori();
		
		assertTrue(valori.get(TipoTerritorio.DESERTO) == 4);
		
		
	}

}
