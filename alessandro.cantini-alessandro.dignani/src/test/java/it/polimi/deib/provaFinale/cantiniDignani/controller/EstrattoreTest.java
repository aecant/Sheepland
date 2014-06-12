package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EstrattoreTest {
	DatiPartita dati;
	Partita partita;
	Territorio t1 = Mappa.getMappa().getTerritori()[1];
	Territorio t2 = Mappa.getMappa().getTerritori()[2];
	Territorio t3 = Mappa.getMappa().getTerritori()[3];

	Strada s[] = Mappa.getMappa().getStrade();

	@Before
	public void setUp() {
		partita = new Partita(Arrays.asList("esempio1", "esempio2", "esempio3", "esempio4"));

		GestorePartita gestore = new GestorePartita(partita, null, null);
		gestore.faseIniziale.disponiPecore();
		gestore.faseIniziale.disponiTessereIniziali();
		gestore.faseIniziale.distribuisciDenari();

		for (int i = 0; i < partita.getPastori().size(); i++) {
			partita.getPastori().get(i).muoviIn(s[i]);
		}
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

	@Test
	public void testStradeLibere() {
		boolean[] sl = Estrattore.stradeLibere(partita);
		List<Integer> stradeOcc = new ArrayList<Integer>();

		for (Pastore p : partita.getPastori()) {
			int stradaOcc = p.getStrada().getCodice();
			stradeOcc.add(stradaOcc);
			assertFalse(sl[stradaOcc]);
		}

		for (int i = 0; i < sl.length; i++) {
			if (!stradeOcc.contains(i)) {
				assertTrue(sl[i]);
			}
		}
		
		Partita partita2 = new Partita(Arrays.asList("esempio1", "esempio2", "esempio3", "esempio4"));
		sl = Estrattore.stradeLibere(partita2);
		for (boolean b : sl) {
			assertTrue(b);
		}
		
		
	}

	@Test
	public void testStradeLibereGratisEAPagamento() {
		List<List<Integer>> stradeGratis = new ArrayList<List<Integer>>();
		stradeGratis.add(0, Arrays.asList(5, 8, 7));
		stradeGratis.add(1, Arrays.asList(7, 17));
		stradeGratis.add(2, Arrays.asList(17, 23));
		stradeGratis.add(3, Arrays.asList(23, 29));

		for (int i = 0; i < stradeGratis.size(); i++) {
			boolean[] sl = Estrattore.stradeLibere(partita);
			boolean[] slg = Estrattore.stradeLibereGratis(partita, s[i]);
			boolean[] slap = Estrattore.stradeLibereAPagamento(partita, s[i]);
			for (int j = 0; j < slg.length; j++) {
				if (stradeGratis.get(i).contains(j)) {
					assertTrue(sl[j]);
					assertTrue(slg[j]);
					assertFalse(slap[j]);
				}
			}
		}
	}

	@Test
	public void testGetPecora() {
		partita.getGregge().aggiungi(new Pecora(t1, false));
		Pecora p = Estrattore.getPecora(partita, t1.getCodice(), TipoAnimale.PECORA);
		assertEquals(p.getPosizione(), t1);
		assertEquals(p.getTipoAnimale(), TipoAnimale.PECORA);
	}
	
	
	@Test
	public void testTessereInCima() {
		Tessera[] tessere = Estrattore.tessereInCima(partita);
		for(Tessera t : tessere) {
			assertEquals(t.getCosto(), 0);
		}
	}
	
	@Test
	public void testGiocatori() {
		Giocatore[] giocatori = Estrattore.giocatori(partita);
		
		for(Giocatore g : partita.getGiocatori()) {
			assertTrue(Utilita.contiene(giocatori, g));
		}
	}
	
	@Test
	public void recintiIniziali() {
		Integer[] posRecIn = Estrattore.recintiIniziali(partita);
		partita.getRecinti().aggiungi(s[4]);
		partita.getRecinti().aggiungi(s[5]);
		assertFalse(Utilita.contiene(posRecIn, 4));
		assertFalse(Utilita.contiene(posRecIn, 5));
	}
	
	private void eliminaPecora(Territorio t) {
		for (Pecora p : partita.getGregge().getPecore()) {
			if (p.getPosizione().equals(t)) {
				partita.getGregge().rimuovi(p);
			}
		}
	}
	
}
