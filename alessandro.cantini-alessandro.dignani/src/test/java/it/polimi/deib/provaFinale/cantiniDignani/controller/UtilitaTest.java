package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UtilitaTest {
	Integer[] a1 = { 1, 2, 3 };
	Integer[] a2 = { 1, 2, 2 };
	List<Integer> al1;
	List<Integer> al2;
	List<Object> originale, copia;
	Object oggettoGenerico;

	@Before
	public void setUp() {
		al1 = new ArrayList<Integer>();
		al2 = new ArrayList<Integer>();
		originale = new ArrayList<Object>();
		oggettoGenerico = new Object();

		al1.add(1);
		al1.add(2);
		al1.add(3);
		al2.add(1);
		al2.add(2);
		al2.add(2);

		originale.add(oggettoGenerico);
		copia = Utilita.copia(originale);
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
		assertTrue(Utilita.contiene(a1, 1));
		assertTrue(Utilita.contiene(al1, 1));

		assertFalse(Utilita.contiene(a1, 4));
		assertFalse(Utilita.contiene(a2, 4));

	}

	@Test
	public void testCopia() {
		assertTrue(copia.contains(oggettoGenerico));
		copia.remove(oggettoGenerico);
		assertFalse(copia.contains(oggettoGenerico));
		assertTrue(originale.contains(oggettoGenerico));
		
//		ArrayList<Agnello> agnelli = new ArrayList<Agnello>();
//		Agnello agnello = new Agnello(null, false);
//		agnelli.add(agnello);
//		ArrayList<Agnello> agnelliCopia = Utilita.copia(agnelli);
//		agnello.invecchia();
//		assertEquals(agnelli.get(0).getEta(), 1);
//		assertEquals(agnelliCopia.get(0).getEta(), 0);
	}

}
