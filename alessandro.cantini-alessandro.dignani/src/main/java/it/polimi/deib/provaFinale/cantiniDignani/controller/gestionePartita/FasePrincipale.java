package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utilita;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioTurno;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoLupo;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecoraNera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaTipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Agnello;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pecora;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Collection;

public class FasePrincipale extends FasePartita {

	private final GestoreMossa gestoreMossa;

	public FasePrincipale(GestorePartita gestore) {
		super(gestore);
		gestoreMossa = new GestoreMossa(gestore);
	}

	@Override
	public void esegui() {
		while (!recintiInizialiFiniti()) {
			for (Giocatore g : partita.getGiocatori()) {
				gestioneTurno(g);
				movimentoLupo();
				market();
			}
		}
	}

	protected void trasformaEInvecchiaAgnelli() {

		for (Pecora pec : Utilita.copia(partita.getGregge().getPecore())) {
			if (pec.getTipoAnimale().equals(TipoAnimale.AGNELLO)) {
				Agnello a = (Agnello) pec;
				if (a.getEta() == Costanti.ETA_MAX_AGNELLO) {
					partita.getGregge().trasformaAgnelloInPecora(a);
				} else {
					a.invecchia();
				}
			}
		}
	}

	private void gestioneTurno(Giocatore giocatore) {

		movimentoPecoraNera();

		turnoGiocatore(giocatore);
	}

	private void turnoGiocatore(Giocatore giocatore) {
		TipoMossa mossaPrecedente = null;
		boolean pastoreMosso = false;
		Pastore pastore = null;

		trasformaEInvecchiaAgnelli();

		partita.setGiocatoreDiTurno(giocatore);

		gestore.inviaEventoATutti(new InizioTurno(giocatore.getNome()));

		if (gestore.dueGiocatori) {
			gestore.inviaEvento(new RichiestaPastore(), giocatore);
			SceltaPastore scelta = (SceltaPastore) gestore.aspettaEvento(SceltaPastore.class);
			Pastore temp = scelta.getPastore();
			for (Pastore p : partita.getPastori()) {
				if (temp.equals(p)) {
					pastore = p;
				}
			}
		} else {
			pastore = giocatore.getPastori().get(0);
		}

		if (pastore == null) {
			throw new NullPointerException("A questo punto il pastore deve essere assegnato");
		}

		for (int numMossa = 1; numMossa <= Costanti.NUM_MOSSE; numMossa++) {
			Collection<TipoMossa> mosseDisponibili = gestoreMossa.creaMosseDisponibili(numMossa, pastoreMosso, mossaPrecedente, pastore, giocatore.getDenaro());

			gestore.inviaEvento(new RichiestaTipoMossa(mosseDisponibili, numMossa), giocatore);

			TipoMossa tipoMossa = ((SceltaMossa) gestore.aspettaEvento(SceltaMossa.class)).getMossa();

			mossaPrecedente = tipoMossa;
			if (tipoMossa == TipoMossa.MUOVI_PASTORE) {
				pastoreMosso = true;
			}

			gestoreMossa.effettuaMossa(pastore, tipoMossa);
		}
	}

	private void market() {
		// TODO Auto-generated method stub

	}

	private void movimentoLupo() {
		boolean tutteStradeOccupate = true;

		int lancio = gestore.lanciaDado(MotivoLancioDado.MOVIMENTO_LUPO);

		Territorio origine = partita.getLupo().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);

		for (int i = 1; i < 6; i++) {
			if (movimentoPossibile(origine, i)) {
				tutteStradeOccupate = false;
				break;
			}
		}

		if (movimentoPossibile(origine, lancio) || tutteStradeOccupate) {
			partita.getLupo().muoviIn(destinazione);
			gestore.inviaEventoATutti(new MovimentoLupo(origine.getCodice(), destinazione.getCodice()));
		}
	}

	private void movimentoPecoraNera() {
		int lancio = gestore.lanciaDado(MotivoLancioDado.MOVIMENTO_PECORA_NERA);

		Territorio origine = partita.getGregge().getPecoraNera().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);
		if (movimentoPossibile(origine, lancio)) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
			gestore.inviaEventoATutti(new MovimentoPecoraNera(origine.getCodice(), destinazione.getCodice()));
		}
	}

	private boolean recintiInizialiFiniti() {
		return partita.getRecinti().getRecintiIniziali().size() == Costanti.NUM_RECINTI_INIZIALI;
	}

	private boolean movimentoPossibile(Territorio origine, int dado) {
		Territorio destinazione = Mappa.getMappa().transizione(origine, dado);
		if (!origine.equals(destinazione) && partita.stradaLibera(new Strada(origine, destinazione))) {
			return true;
		}
		return false;
	}

}
