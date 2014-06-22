package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Estrattore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizioneInizialePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Utilita;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FaseIniziale extends FasePartita {

	private static final Logger LOGGER = Logger.getLogger(FaseIniziale.class.getName());

	public FaseIniziale(GestorePartita gestore) {
		super(gestore);
	}

	@Override
	public void esegui() {
		giroPosizionamentoPastore(0);
		if (gestore.isDueGiocatori()) {
			giroPosizionamentoPastore(1);
		}
	}

	private void giroPosizionamentoPastore(int numPastore) {
		for (Giocatore giocatore : partita.getGiocatori()) {
			if (gestore.giocatoreOffline(giocatore)) {
				continue;
			}

			boolean[] stradeLibere = Estrattore.stradeLibere(partita);
			gestore.inviaEvento(new RichiestaPosizioneInizialePastore(stradeLibere), giocatore);
			int codStrada;
			try {
				codStrada = gestore.aspettaMossa(giocatore);
			} catch (GiocatoreDisconnessoException e) {
				// se il giocatore se e' disconnesso in questa fase i suoi
				// pastori vengono posizionati sulla prima strada libera
				LOGGER.log(Level.FINE, "giocatore disconnesso", e);
				codStrada = Utilita.indiciTrue(stradeLibere).get(0);
			}

			Strada strada = Mappa.getMappa().getStrade()[codStrada];
			giocatore.getPastori().get(numPastore).muoviIn(strada);

			gestore.inviaEventoATutti(new PosizionamentoPastore(giocatore.getNome(), codStrada, Estrattore.giocatori(partita)));

		}
	}

}
