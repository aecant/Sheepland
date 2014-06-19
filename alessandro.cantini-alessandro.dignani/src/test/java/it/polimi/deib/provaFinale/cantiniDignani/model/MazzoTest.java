package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mazzo;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mazzo.MazzoFinitoException;

import org.junit.Before;
import org.junit.Test;

public class MazzoTest {
	Mazzo mazzo1, mazzo2, mazzo3;

	@Before
	public void setUp() {
		mazzo1 = new Mazzo();
		mazzo2 = new Mazzo();
	}

	/**
	 * Verifica il sollevamento di una MazzoFinitoException se si cerca di
	 * pescare una tessera in piu' del numero di tessere per ogni pila {@see
	 * Costanti.NUM_TESSERE_PER_TIPO}
	 */
	@Test(expected = MazzoFinitoException.class)
	public void testMazzoFinito() {
		for (int i = 0; i < Costanti.NUM_TESSERE_PER_TIPO + 1; i++) {
			mazzo1.prelevaTessera(TipoTerritorio.BOSCO);
			if(i == Costanti.NUM_TESSERE_PER_TIPO) {
				assertTrue(mazzo1.getTessereRimaste(TipoTerritorio.BOSCO) == 0);
			}
		}
	}

	/**
	 * Verifica il sollevamento di una IllegalArgumentException se si cerca di
	 * prelevare una tessera di tipo Sheepsburg
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPrelevaSheepsburg() {
		mazzo1.prelevaTessera(TipoTerritorio.SHEEPSBURG);
	}

	/**
	 * Instanzia un nuovo mazzo e preleva due tessere
	 */
	@Before
	public void setUpCarteRimaste() {
		mazzo3 = new Mazzo();

		mazzo3.prelevaTessera(TipoTerritorio.MONTAGNA);
		mazzo3.prelevaTessera(TipoTerritorio.MONTAGNA);
	}

	/**
	 * Verifica che le cartie rimanenti del tipo estratto in precedenza siano
	 * esattamente il numero iniziale - 2
	 */
	@Test
	public void testGetTessereRimaste() {
		assertTrue(mazzo3.getTessereRimaste(TipoTerritorio.MONTAGNA) == Costanti.NUM_TESSERE_PER_TIPO - 2);
		mazzo3.prelevaTessera(TipoTerritorio.MONTAGNA);
		assertTrue(mazzo3.getTessereRimaste(TipoTerritorio.MONTAGNA) == Costanti.NUM_TESSERE_PER_TIPO - 3);

	}

	/**
	 * Verifica che il valore della carta in cima sia il valore massimo - 2
	 */
	@Test
	public void testLeggiTesseraInCima() {
		assertTrue(mazzo3.leggiTesseraInCima(TipoTerritorio.MONTAGNA).getCosto() == Costanti.MAX_VALORE_TESSERA - 2);
	}

	/**
	 * Preleva una carta per ogni tipo (a parte SHEEPSBURG), verifica che la
	 * carta prelevata sia corretta e che l'abbia effettivamente prelevata
	 */
	@Test
	public void testPrelevaTessera() {
		for (TipoTerritorio tipo : TipoTerritorio.values())
			if (tipo != TipoTerritorio.SHEEPSBURG) {
				Tessera tesseraPrelevata = mazzo2.prelevaTessera(tipo);
				assertEquals(tipo, tesseraPrelevata.getTipo());
				assertFalse(tesseraPrelevata.equals(mazzo2.leggiTesseraInCima(tipo)));
			}
	}
}
