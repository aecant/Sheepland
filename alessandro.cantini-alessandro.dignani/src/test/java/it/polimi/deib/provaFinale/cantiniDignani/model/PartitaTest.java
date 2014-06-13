package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	Partita partita;
	Strada s1, s2, s3;
	Territorio t0, t1, t4, t7;
	List<String> nomi;
	List<Pecora> pecore;

	@Before
	public void setUp() {
		t0 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t1 = new Territorio(1, TipoTerritorio.MONTAGNA);
		t4 = new Territorio(4, TipoTerritorio.DESERTO);
		t7 = new Territorio(7, TipoTerritorio.LAGO);

		s1 = new Strada(t0, t1);
		s2 = new Strada(t0, t4);
		s3 = new Strada(t0, t7);
		
		nomi = Arrays.asList("esempio1", "esempio2", "esempio3");
		
		partita = new Partita(nomi);

		partita.getRecinti().aggiungi(s1);
		
		partita.getGiocatori().get(0).getPastori().get(0).muoviIn(s2);
		
		pecore = Arrays.asList(new Pecora(t0, false), new Agnello(t1, true), new Pecora(t1, true));
		for(Pecora pec : pecore) {
			partita.getGregge().aggiungi(pec);
		}
	}

	@Test
	public void testPartita() {
		List<String> listaNomi = new ArrayList<String>();
		for(Giocatore g : partita.getGiocatori()) {
			listaNomi.add(g.getNome());
		}
		
		assertTrue(listaNomi.containsAll(nomi));
		
		for(int i = 0; i < partita.getGiocatori().size(); i++) {
			
		}
	}
	
	
	@Test
	public void testStradaLibera() {
		assertTrue(partita.stradaLibera(s3));
		assertFalse(partita.stradaLibera(s1));
		assertFalse(partita.stradaLibera(s2));
	}	
	
	@Test 
	public void territorioLibero() {
		assertFalse(partita.territorioLibero(t0));
		assertFalse(partita.territorioLibero(t1));
		assertTrue(partita.territorioLibero(t4));
		assertTrue(partita.territorioLibero(t7));
	}
	
	@Test
	public void testGetTutteLePedineSuStrada() {
		List<PedinaSuStrada> pedineSuStrada = partita.getTutteLePedineSuStrada();
		List<Strada> strade = new ArrayList<Strada>();
		for(PedinaSuStrada ped : pedineSuStrada) {
			strade.add(ped.getStrada());
		}
		assertTrue(strade.contains(s1));
		assertTrue(strade.contains(s2));
		assertFalse(strade.contains(s3));
	}
	
	@Test
	public void testGetGiocatore() {
		for(Giocatore g : partita.getGiocatori()) {
			Pastore p = g.getPastori().get(0);
			assertEquals(partita.getGiocatore(p), g);
		}
	}

}
