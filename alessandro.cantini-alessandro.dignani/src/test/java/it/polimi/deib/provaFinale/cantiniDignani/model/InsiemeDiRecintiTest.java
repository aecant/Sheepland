package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class InsiemeDiRecintiTest {
	InsiemeDiRecinti recinti;

	@Before
	public void setUp() {
		recinti = new InsiemeDiRecinti();
	}

	/**
	 * Inserisco un numero di recinti tali da riempire l'arraylist di recinti
	 * iniziali e meta' dei recinti finali, in seguito controllo che le
	 * dimensioni siano corrette. Le strade sono prese da @link{Mappa}
	 */
	@Test
	public void testAggiungi() {
		for (int i = 0; i < Costanti.NUM_RECINTI_INIZIALI + Costanti.NUM_RECINTI_FINALI / 2; i++) {
			recinti.aggiungi(Mappa.getMappa().getStrade()[i]);
		}
		assertEquals(recinti.getRecintiIniziali().size(), Costanti.NUM_RECINTI_INIZIALI);
		assertEquals(recinti.getRecintiFinali().size(), Costanti.NUM_RECINTI_FINALI / 2);
		assertEquals(recinti.getListaRecinti().size(), Costanti.NUM_RECINTI_INIZIALI + Costanti.NUM_RECINTI_FINALI / 2);
	}

}
