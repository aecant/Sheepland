package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;

import org.junit.Before;
import org.junit.Test;

public class MazzoTest {
	Mazzo mazzo;	
	
	@Before
	public void setUp(){
		mazzo = new Mazzo();
	}
	

	@Test (expected=IllegalArgumentException.class)
	public void testPrelevaCarta() {
		for(int i=0; i<Costanti.MAX_VALORE_TESSERA+2; i++)
			mazzo.prelevaCarta(TipoTerritorio.BOSCO);
	}
}
