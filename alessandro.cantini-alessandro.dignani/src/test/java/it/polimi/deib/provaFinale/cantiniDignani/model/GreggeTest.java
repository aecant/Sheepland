package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;

import java.util.List;

import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.Gregge;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;

import org.junit.Before;
import org.junit.Test;

public class GreggeTest {
	Gregge g1, g2, g3;
	Pecora p1, p2, p3;
	Agnello agnello;
	Territorio t1, t2;
	List<Pecora> pecoreSuTerr;

	@Before
	public void setUp() {
		t1 = new Territorio(0, TipoTerritorio.SHEEPSBURG);
		t2 = new Territorio(5, TipoTerritorio.DESERTO);

		p1 = new Pecora(t1, false);
		p2 = new Pecora(t1, true);
		p3 = new Pecora(t2, true);

		agnello = new Agnello(t1, false);
		g1 = new Gregge();
		g2 = new Gregge();
		g3 = new Gregge();

		g1.aggiungi(p1);
		g1.rimuovi(p1);

		g2.aggiungi(p1);

		g3.aggiungi(agnello);
		g3.aggiungi(p1);
		g3.aggiungi(p2);
		g3.aggiungi(p3);

	}

	@Test
	public void testAggiungiRimuovi() {
		assertFalse(g1.getPecore().contains(p1));
		assertTrue(g2.getPecore().contains(p1));
	}

	@Test
	public void testTrasformaAgnelloInPecora() {
		assertTrue(g3.getPecore().contains(agnello));
		assertEquals(g3.getPecore().get(0).getTipoAnimale(), TipoAnimale.AGNELLO);
		g3.trasformaAgnelloInPecora(agnello);
		assertTrue(g3.getPecore().get(0).getTipoAnimale() == TipoAnimale.PECORA || g3.getPecore().get(0).getTipoAnimale() == TipoAnimale.MONTONE);

	}

}
