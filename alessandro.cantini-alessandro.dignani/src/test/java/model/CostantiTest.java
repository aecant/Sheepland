package model;

import static org.junit.Assert.*;
import model.Costanti;

import org.junit.Test;

public class CostantiTest {
	
	
	@Test
	public void test() {
		assertEquals(Costanti.MAPPA.length, Costanti.TERRITORIO_CODICE.length);
	}

}
