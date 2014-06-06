package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GestorePartitaTest {
	List<String> listaNomi = new ArrayList<String>();
	Partita partita;
	GestorePartita gestore;
	
	@Before
	public void setUp() throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalAccessException {
		Collections.addAll(listaNomi, "esempio1", "esempio2", "esempio3");
		partita = new Partita(listaNomi);		
		gestore = new GestorePartita(partita, null, null);
		
		Field fieldPartita = GestorePartita.class.getDeclaredField("partita");
		fieldPartita.setAccessible(true);
		fieldPartita.set(gestore, partita);
	}
	
	
	@Test
	public void testDistribuisciDenari() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Method method = GestorePartita.class.getDeclaredMethod("distribuisciDenari", (Class<?>[]) null);
		method.setAccessible(true);
		method.invoke(gestore, (Object[]) null);
		
		for(Giocatore g : partita.getGiocatori()) {
			assertEquals(g.getDenaro(), Costanti.DENARO_INIZIALE);
		}
		
		listaNomi.remove(0);
		partita = new Partita(listaNomi);
		gestore = new GestorePartita(partita, null, null);
		method.invoke(gestore, (Object[]) null);
		
		for(Giocatore g : partita.getGiocatori()) {
			assertEquals(g.getDenaro(), Costanti.DENARO_INIZIALE_DUE_GIOCATORI);
		}
	}
	
	@Test
	public void disponiPecora() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Method method = GestorePartita.class.getDeclaredMethod("disponiPecore", (Class<?>[]) null);
		method.setAccessible(true);
		method.invoke(gestore, (Object[]) null);
		
		DatiTerritorio[] dati = Estrattore.datiTerritori(partita);
		
		for(DatiTerritorio d : dati) {
			assertEquals(d.getNumOvini(), 1);
		}
	}

}
