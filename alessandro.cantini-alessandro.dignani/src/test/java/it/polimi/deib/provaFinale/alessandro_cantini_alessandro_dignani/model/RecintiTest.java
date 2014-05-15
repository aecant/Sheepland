package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Costanti;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Mazzo;
import it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.model.Recinti;

import org.junit.Before;
import org.junit.Test;

public class RecintiTest {

	@Before
	public void setUp() {
		//TODO da fare quando avremo implementato aggiungi in recinti
		for(int i=0; i < Costanti.NUM_RECINTI+4; i++){
			Recinti.getRecinti().aggiungi(null);
		}
	}
	
	@Test
	public void test() {
		
	}

}
