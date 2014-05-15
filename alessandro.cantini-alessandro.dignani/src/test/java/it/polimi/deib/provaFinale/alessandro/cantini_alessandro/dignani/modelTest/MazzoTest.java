package it.polimi.deib.provaFinale.alessandro.cantini_alessandro.dignani.modelTest;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;

import org.junit.Before;
import org.junit.Test;

public class MazzoTest {
	Mazzo mazzo1, mazzo2;	
	
	
	@Test
	public void testMazzo() {
		assertEquals(mazzo1.getMazzo(), mazzo2.getMazzo());
	}

	/*@Test
	public void testPrelevaCarta() {
	}*/

}
