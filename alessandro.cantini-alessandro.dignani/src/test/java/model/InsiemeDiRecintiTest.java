package model;

import static org.junit.Assert.*;
import model.Costanti;
import model.InsiemeDiRecinti;
import model.Mappa;

import org.junit.Before;
import org.junit.Test;

public class InsiemeDiRecintiTest {
	InsiemeDiRecinti recinti;
	
	/**
	 * Inserisco un numero di recinti tali da riempire l'arraylist di recinti iniziali e meta' dei
	 * recinti finali, in seguito controllo che le dimensioni siano corrette
	 */
	@Before
	public void setUp() {
		recinti = new InsiemeDiRecinti();
		for(int i = 0; i < Costanti.NUM_RECINTI_INIZIALI + Costanti.NUM_RECINTI_FINALI / 2; i++)
			recinti.aggiungi(Mappa.getMappa().getStrade()[i]);
	}
	
	
	@Test
	public void testAggiungi() {
		assertTrue(recinti.getRecintiIniziali().size() == Costanti.NUM_RECINTI_INIZIALI);
		assertTrue(recinti.getRecintiFinali().size() == Costanti.NUM_RECINTI_FINALI / 2);
		assertTrue(recinti.getListaRecinti().size() == Costanti.NUM_RECINTI_INIZIALI + Costanti.NUM_RECINTI_FINALI / 2);
	}

}
