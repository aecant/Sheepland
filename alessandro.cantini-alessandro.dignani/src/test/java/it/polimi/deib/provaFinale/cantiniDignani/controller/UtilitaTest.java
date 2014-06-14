package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class UtilitaTest {
	Integer[] a1 = { 1, 2, 3 };
	Integer[] a2 = { 1, 2, 2 };
	List<Integer> al1;
	List<Integer> al2;

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
	public void testContieneDuplicati() {
		assertFalse(Utilita.contieneDuplicati(a1));
		assertFalse(Utilita.contieneDuplicati(al1));

		assertTrue(Utilita.contieneDuplicati(a2));
		assertTrue(Utilita.contieneDuplicati(al2));

	}

	@Test
	public void testContiene() {
		assertFalse(Utilita.contiene(a1, 4));
		assertFalse(Utilita.contiene(a2, 4));
	}

	@Test
	public void testRendiSerializzabile() {
		// creo una mappa perche' il keyset di una mappa non e' serializzabile
		Map<Integer, Integer> mappa = new HashMap<Integer, Integer>();
		mappa.put(1, 2);
		mappa.put(2, 3);

		Collection<Integer> nonSerializzabile = mappa.keySet();

		assertFalse(nonSerializzabile instanceof Serializable);

		Collection<Integer> serializzabile = Utilita.rendiSerializzabile(nonSerializzabile);

		assertTrue(serializzabile instanceof Serializable);

		assertTrue(serializzabile.containsAll(nonSerializzabile));
		for (Integer i : serializzabile) {
			nonSerializzabile.contains(i);
		}
	}

	@Test
	public void testCopia() {
		Collection<Object> originale = new ArrayList<Object>();
		Object oggettoGenerico = new Object();
		
		originale.add(oggettoGenerico);
		Collection<Object> copia = Utilita.copia(originale);
		
		assertTrue(copia.contains(oggettoGenerico));
		copia.remove(oggettoGenerico);
		assertFalse(copia.contains(oggettoGenerico));
		assertTrue(originale.contains(oggettoGenerico));
	
		
	}

}
