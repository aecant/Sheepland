package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizioneInizialePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;

public class FaseIniziale extends FasePartita {

	public FaseIniziale(GestorePartita gestore) {
		super(gestore);
	}

	@Override
	public void esegui() {
		giroPosizionamentoPastore(0);
		if (gestore.dueGiocatori) {
			giroPosizionamentoPastore(1);
		}
	}

	private void giroPosizionamentoPastore(int numPastore) {
		for (Giocatore g : partita.getGiocatori()) {
			boolean[] stradeLibere = Estrattore.stradeLibere(partita);
			gestore.inviaEvento(new RichiestaPosizioneInizialePastore(stradeLibere), g);
			int codStrada = gestore.aspettaMossa();

			Strada strada = Mappa.getMappa().getStrade()[codStrada];
			g.getPastori().get(numPastore).muoviIn(strada);

			gestore.inviaEventoATutti(new PosizionamentoPastore(g.getNome(), codStrada, Estrattore.giocatori(partita)));

		}
	}

}
