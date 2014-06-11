package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static org.junit.Assert.assertEquals;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Abbattimento;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CliTest {

	Cli cli;
	
	@Test
	public void test() {
		String str = "1";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		cli = new Cli(is);
		
		List<TipoAnimale> l1 = Arrays.asList(TipoAnimale.AGNELLO, TipoAnimale.PECORA, TipoAnimale.MONTONE);
		List<TipoAnimale> l2 = Arrays.asList(TipoAnimale.AGNELLO, TipoAnimale.MONTONE);
		int t1 = 1;
		int t2 = 2;

		Abbattimento abb = new Abbattimento(cli.richiestaPecoraDaAbbattere(t1, l1, t2, l2), true);
		assertEquals(abb.getTipoOvino(), TipoAnimale.AGNELLO);
	}

}
