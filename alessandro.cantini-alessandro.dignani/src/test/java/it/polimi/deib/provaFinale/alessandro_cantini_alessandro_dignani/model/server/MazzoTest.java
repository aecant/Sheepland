package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.server;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Costanti;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Tessera;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo.MazzoFinitoException;

import org.junit.Before;
import org.junit.Test;

public class MazzoTest {
	Mazzo mazzo1, mazzo2, mazzo3;
	
	@Before
	public void setUp(){
		mazzo1 = new Mazzo();
		mazzo2 = new Mazzo();
	}
	

	@Test (expected=MazzoFinitoException.class)
	public void testMazzoFinito() {
		for(int i=0; i<Costanti.MAX_VALORE_TESSERA+2; i++)
			mazzo1.prelevaTessera(TipoTerritorio.BOSCO);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testPrelevaSheepsburg() {
		mazzo1.prelevaTessera(TipoTerritorio.SHEEPSBURG);
	}
	
	
	@Before 
	public void setUpCarteRimaste() {
		mazzo3 = new Mazzo();
	
		mazzo3.prelevaTessera(TipoTerritorio.MONTAGNA);
		mazzo3.prelevaTessera(TipoTerritorio.MONTAGNA);
	}
	
	@Test 
	public void testGetTessereRimaste() {
		assertTrue(mazzo3.getTessereRimaste(TipoTerritorio.MONTAGNA) == Costanti.NUM_TESSERE_PER_TIPO-2);
	}
	
	@Test
	public void testLeggiTesseraInCima() {
		assertTrue(mazzo3.leggiTesseraInCima(TipoTerritorio.MONTAGNA).getCosto() == Costanti.MAX_VALORE_TESSERA-2);
	}
	
	/**
	 * Preleva una carta per ogni tipo (a parte SHEEPSBURG), verifica che la carta prelevata sia corretta
	 * e che l'abbia effettivamente prelevata
	 */
	@Test
	public void testPrelevaTessera() {
		for(TipoTerritorio tipo : TipoTerritorio.values())
			if(tipo != TipoTerritorio.SHEEPSBURG) {
				Tessera tesseraPrelevata = mazzo2.prelevaTessera(tipo);
				assertEquals(tipo, tesseraPrelevata.getTipo());
				assertFalse(tesseraPrelevata.equals(mazzo2.leggiTesseraInCima(tipo)));
			}
	}
}
