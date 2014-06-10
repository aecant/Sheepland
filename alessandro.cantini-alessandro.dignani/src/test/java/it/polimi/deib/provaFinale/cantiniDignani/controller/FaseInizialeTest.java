package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FaseInizialeTest {
	List<String> listaNomi = new ArrayList<String>();
	Partita partita;
	FaseIniziale faseIniziale;
	GestorePartita gestore;

	@Before
	public void setUp() {
		Collections.addAll(listaNomi, "esempio1", "esempio2", "esempio3", "esempio4");
		partita = new Partita(listaNomi);
		gestore = new GestorePartita(partita, null, null);
		faseIniziale = new FaseIniziale(gestore);
	}

	/**
	 * Controlla che i denari siano correttamente distribuiti per ogni
	 * configurazione di giocatori
	 */
	@Test
	public void testDistribuisciDenari() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Method method = FaseIniziale.class.getDeclaredMethod("distribuisciDenari", (Class<?>[]) null);
		method.setAccessible(true);

		for (int i = Costanti.NUM_MAX_GIOCATORI; i >= Costanti.NUM_MIN_GIOCATORI; i--) {
			partita = new Partita(listaNomi);
			gestore = new GestorePartita(partita, null, null);
			faseIniziale = new FaseIniziale(gestore);
			method.invoke(faseIniziale, (Object[]) null);

			for (Giocatore g : partita.getGiocatori()) {
				
				if (partita.getGiocatori().size() == 2) {
					assertTrue(g.getDenaro() == Costanti.DENARO_INIZIALE_DUE_GIOCATORI);
				} else {
					assertTrue(g.getDenaro() == Costanti.DENARO_INIZIALE);
				}
			}
			
			listaNomi.remove(0);
		}

	}

	/**
	 * Controlla che ogni territorio abbia esattamente una pecora, un montone o
	 * un agnello
	 */
	@Test
	public void disponiPecora() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Method method = FaseIniziale.class.getDeclaredMethod("disponiPecore", (Class<?>[]) null);
		method.setAccessible(true);
		method.invoke(faseIniziale, (Object[]) null);

		DatiTerritorio[] dati = Estrattore.datiTerritori(partita);

		for (DatiTerritorio d : dati) {
			assertEquals(d.getNumOvini(), 1);
		}
	}

	/**
	 * Controlla che dopo aver disposto le tessere iniziali ogni giocatore abbia
	 * esattamente una tessera e che non siano state distribuite tessere
	 * duplicate
	 */
	@Test
	public void testDisponiTessereIniziali() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Method method = FaseIniziale.class.getDeclaredMethod("disponiTessereIniziali", (Class<?>[]) null);
		method.setAccessible(true);
		method.invoke(faseIniziale, (Object[]) null);

		List<Tessera> listaTessere = new ArrayList<Tessera>();

		for (Giocatore g : partita.getGiocatori()) {
			assertEquals(g.getTessere().size(), 1);
			listaTessere.addAll(g.getTessere());
		}

		assertFalse(Utilita.contieneDuplicati(listaTessere));

	}

}
