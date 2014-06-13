package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecora;

import org.junit.Before;
import org.junit.Test;

public class GestoreCodaTest {

	GestoreCoda<Evento> gestore;

	final static Evento EVENTO = new LancioDado(6, null);
	Evento daAspettare;

	@Before
	public void setUp() {
		gestore = new GestoreCoda<Evento>();
	}

	@Test
	public void testAspettaEvento() {
		gestore.aggiungi(EVENTO);
		assertTrue(gestore.aspetta() == EVENTO);
	}
	
	@Test (timeout = 1000)
	public void testAspettaEventoThread() {
		daAspettare = null;
		
		Thread aggEvento = new ThreadAggiungiEvento();
		aggEvento.start();
		
		daAspettare = gestore.aspetta();
		
		assertTrue(daAspettare == EVENTO);
	}
	
	@Test (expected = RuntimeException.class)
	public void testAspettaEventoSpecifico() {
		gestore.aggiungi(EVENTO);
		gestore.aspetta(MovimentoPecora.class);
	}
	
	class ThreadAggiungiEvento extends Thread {

		@Override
		public void run() {
			assertTrue(daAspettare == null);
			gestore.aggiungi(EVENTO);
		}
	}

}
