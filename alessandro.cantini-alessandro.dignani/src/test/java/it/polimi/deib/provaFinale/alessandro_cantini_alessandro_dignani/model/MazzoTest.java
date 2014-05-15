package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;

import org.junit.Before;
import org.junit.Test;

public class MazzoTest {
	Mazzo mazzo1, mazzo2;	
	
	@Before
	public void setUp(){
		mazzo1 = Mazzo.getMazzo();
		mazzo2 = Mazzo.getMazzo();
	}
	
	
	@Test
	public void testMazzo() {
		assertEquals(mazzo1, mazzo2);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testPrelevaCarta() {
		for(int i=0; i<Costanti.MAX_VALORE_TESSERA+2; i++)
			Mazzo.getMazzo().prelevaCarta(TipoTerritorio.BOSCO);
	}
}
