package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TesseraTest {
	Tessera tessera;
	TipoTerritorio tipo = TipoTerritorio.CAMPO;
	int costo = 1;
	
	@Before
	public void setUpd() {
		tessera = new Tessera(tipo, costo);
	}
	
	@Test
	public void test() {
		assertEquals(tessera.getTipo(), tipo);
		assertEquals(tessera.getCosto(), costo);
	}

}
