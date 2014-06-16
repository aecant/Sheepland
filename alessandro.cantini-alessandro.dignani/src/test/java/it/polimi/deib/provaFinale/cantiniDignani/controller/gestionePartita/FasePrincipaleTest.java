package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import static org.junit.Assert.assertEquals;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class FasePrincipaleTest {

	GestorePartita gestore;
	Partita  partita;

	@Before
	public void setUp() {
		partita = new Partita(Arrays.asList("esempio1", "esempio2", "esempio3"));
		gestore = new GestorePartita(partita, null, null);
	}

	@Test
	public void testTrasformaEInvecchiaAgnelli() {
		Territorio[] t = Mappa.getMappa().getTerritori();
		partita.getGregge().aggiungi(Sorte.agnelloRandom(t[1]));
		partita.getGregge().aggiungi(Sorte.agnelloRandom(t[2]));
		partita.getGregge().aggiungi(Sorte.agnelloRandom(t[3]));
		
		int numAgnelliPrima = numAgnelli(partita);
		
		gestore.getFasePrincipale().trasformaEInvecchiaAgnelli();
		int numAgnelliDopo = numAgnelli(partita);
		
		assertEquals(numAgnelliPrima, numAgnelliDopo);
		
		gestore.getFasePrincipale().trasformaEInvecchiaAgnelli();
		gestore.getFasePrincipale().trasformaEInvecchiaAgnelli();

	
		
		numAgnelliDopo = numAgnelli(partita);
		assertEquals(numAgnelliDopo, 0);
		
	}

	private int numAgnelli(Partita part) {
		int cont = 0;
		for(Pecora pec : part.getGregge().getPecore()) {
			if(pec.getTipoAnimale().equals(TipoAnimale.AGNELLO)) {
				cont++;
			}
		}
		return cont;
	}

}
