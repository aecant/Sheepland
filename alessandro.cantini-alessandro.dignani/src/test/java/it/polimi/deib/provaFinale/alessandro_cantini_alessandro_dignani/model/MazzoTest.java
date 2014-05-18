package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;
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
			mazzo1.prelevaCarta(TipoTerritorio.BOSCO);
	}
	
	
	@Test (expected=IllegalArgumentException.class)
	public void testPrelevaSheepsburg() {
		mazzo1.prelevaCarta(TipoTerritorio.SHEEPSBURG);
	}
	
	/**
	 * Preleva una carta per ogni tipo (a parte SHEEPSBURG) e verifica che la carta prelevata sia corretta
	 */
	@Test
	public void testPrelevaCarta() {
		for(TipoTerritorio terr : TipoTerritorio.values())
			if(terr != TipoTerritorio.SHEEPSBURG)
				assertEquals(terr, mazzo2.prelevaCarta(terr).getTipo());
	}
	
	@Before 
	public void setUpCarteRimaste() {
		mazzo3 = new Mazzo();
	
		mazzo3.prelevaCarta(TipoTerritorio.MONTAGNA);
		mazzo3.prelevaCarta(TipoTerritorio.MONTAGNA);
	}
	
	@Test 
	public void testGetCarteRimaste() {
		assertTrue(mazzo3.getCarteRimaste(TipoTerritorio.MONTAGNA) == Costanti.NUM_TESSERE_PER_TIPO-2);
	}
	
	@Test
	public void testGetCostoCartaInCima() {
		assertTrue(mazzo3.getCostoCartaInCima(TipoTerritorio.MONTAGNA) == Costanti.MAX_VALORE_TESSERA-2);
	}
}
