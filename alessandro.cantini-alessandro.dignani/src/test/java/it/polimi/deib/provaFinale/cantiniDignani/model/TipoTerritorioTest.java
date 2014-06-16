package it.polimi.deib.provaFinale.cantiniDignani.model;

import static org.junit.Assert.*;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import org.junit.Test;

public class TipoTerritorioTest {

	@Test
	public void testValoriTessere() {
		TipoTerritorio[] valoriTessere = TipoTerritorio.valoriTessere();
		
		for(TipoTerritorio tipo : TipoTerritorio.values()) {
			if(tipo != TipoTerritorio.SHEEPSBURG) {
				assertTrue(Utilita.contiene(valoriTessere, tipo));
			}
		}
		
		assertFalse(Utilita.contiene(valoriTessere, TipoTerritorio.SHEEPSBURG));
	}

}
