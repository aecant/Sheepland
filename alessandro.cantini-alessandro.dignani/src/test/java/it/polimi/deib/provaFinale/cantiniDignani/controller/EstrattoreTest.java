package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;

import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class EstrattoreTest {
	DatiPartita dati;
	Partita partita;
	Territorio t1 = Mappa.getMappa().getTerritori()[1];
	Territorio t2 = Mappa.getMappa().getTerritori()[2];
	Territorio t3 = Mappa.getMappa().getTerritori()[3];

	Strada s0 = Mappa.getMappa().getStrade()[0];
	Strada s1 = Mappa.getMappa().getStrade()[0];
	Strada s2 = Mappa.getMappa().getStrade()[0];

	@Before
	public void setUp() throws Exception {
		partita = new Partita(Arrays.asList("esempio1", "esempio2", "esempio3", "esempio4"));

		GestorePartita gestore = new GestorePartita(partita, null, null);
		gestore.faseIniziale.disponiPecore();
		gestore.faseIniziale.disponiTessereIniziali();
		gestore.faseIniziale.distribuisciDenari();
	}

	@Test
	public void testDatiTerritorio() {
		DatiTerritorio[] dati = Estrattore.datiTerritori(partita);

		assertEquals(dati[0].getNumOvini(), 0);
		assertTrue(dati[0].isLupo());
		assertTrue(dati[0].isPecoraNera());

		for (int i = 1; i < dati.length; i++) {
			assertEquals(dati[i].getNumOvini(), 1);
		}

		partita.getGregge().aggiungi(new Pecora(t1, true));
		eliminaPecora(t2);
		partita.getLupo().muoviIn(t1);
		partita.getGregge().getPecoraNera().muoviIn(t3);
		
		dati = Estrattore.datiTerritori(partita);

		assertEquals(dati[1].getNumOvini(), 2);
		assertTrue(dati[1].getNumMontoni() >= 1);
		assertEquals(dati[2].getNumOvini(), 0);
		assertTrue(dati[1].isLupo());
		assertFalse(dati[0].isLupo());
		assertTrue(dati[3].isPecoraNera());
		assertFalse(dati[0].isPecoraNera());

	}

	private void eliminaPecora(Territorio t) {
		for (Pecora p : partita.getGregge().getPecore()) {
			if (p.getPosizione().equals(t)) {
				partita.getGregge().rimuovi(p);
			}
		}
	}

}
