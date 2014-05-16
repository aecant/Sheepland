package it.polimi.deib.provaFinale.alessandro_cantini_alessandro_dignani.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class UtilitaTest {
	Integer[] a1 = {1, 2, 3};
	Integer[] a2 = {1, 2, 2};	
	ArrayList<Integer> al1;
	ArrayList<Integer> al2;
	
	@Before
	public void setUp() {
		al1 = new ArrayList<Integer>();
		al2 = new ArrayList<Integer>();
		
		al1.add(1);
		al1.add(2);
		al1.add(3);
		al2.add(1);
		al2.add(2);
		al2.add(2);
	}

	
	@Test
	public void test() {
		assertFalse(Utilita.contieneDuplicati(a1));
		assertTrue(Utilita.contieneDuplicati(a2));
		assertFalse(Utilita.contieneDuplicati(al1));
		assertTrue(Utilita.contieneDuplicati(al2));
	}

}
