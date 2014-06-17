package it.polimi.deib.provaFinale.cantiniDignani.view.cli;

import static org.junit.Assert.assertEquals;
import static it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale.*;
import static it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia.*;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CliTest {

	Cli cli;

	@Test
	public void test() {
		String str = "1";
		InputStream is = new ByteArrayInputStream(str.getBytes());
		cli = new Cli(is);

		List<Coppia<Integer, TipoAnimale>> oviniDisp = new ArrayList<Coppia<Integer, TipoAnimale>>();
		oviniDisp.add(creaCoppia(1, AGNELLO));
		oviniDisp.add(creaCoppia(1, PECORA));
		oviniDisp.add(creaCoppia(1, MONTONE));

		int indice = cli.richiestaPecoraDaAbbattere(oviniDisp);
		assertEquals(oviniDisp.get(indice).secondo, TipoAnimale.AGNELLO);
	}

}
