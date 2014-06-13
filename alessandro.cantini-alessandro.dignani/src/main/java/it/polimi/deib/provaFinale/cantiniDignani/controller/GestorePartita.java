package it.polimi.deib.provaFinale.cantiniDignani.controller;

import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.InizioTurno;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoLupo;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.MovimentoPecoraNera;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.PosizionamentoPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaPosizioneInizialePastore;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.RichiestaTipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaMossa;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.SceltaPastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mappa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.model.Pastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Strada;
import it.polimi.deib.provaFinale.cantiniDignani.model.Territorio;
import it.polimi.deib.provaFinale.cantiniDignani.rete.InterfacciaServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GestorePartita implements Runnable {

	private final Partita partita;
	private List<String> tuttiGiocatori;
	private final InterfacciaServer connessione;
	private final GestoreMossa gestoreMossa;
	public final FaseIniziale faseIniziale;
	public final FaseFinale faseFinale;
	private final GestoreCoda<Evento> gestoreEventi;

	private boolean dueGiocatori;
	public final int denaroIniziale;

	public GestorePartita(Partita partita, InterfacciaServer connessione, GestoreCoda<Evento> gestoreEventi) {
		this.partita = partita;
		this.connessione = connessione;
		this.gestoreEventi = gestoreEventi;
		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = Costanti.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = Costanti.DENARO_INIZIALE;
		}
		tuttiGiocatori = new ArrayList<String>();
		for (Giocatore g : partita.getGiocatori()) {
			tuttiGiocatori.add(g.getNome());
		}
		gestoreMossa = new GestoreMossa(this);
		faseFinale = new FaseFinale(this);
		faseIniziale = new FaseIniziale(this);
	}

	public void run() {

		faseIniziale.esegui();
		
		selezionePosizioneIniziale();

		gestioneGiro();

		faseFinale.esegui();
	}

	private void gestioneGiro() {
		while (!recintiInizialiFiniti()) {
			for (Giocatore g : partita.getGiocatori()) {
				movimentoPecoraNera();
				gestioneTurno(g);
				movimentoLupo();
				market();
			}
		}
	}

	private void gestioneTurno(Giocatore giocatore) {
		TipoMossa mossaPrecedente = null;
		boolean pastoreMosso = false;
		Pastore pastore;

		partita.setGiocatoreDiTurno(giocatore);

		inviaEventoATutti(new InizioTurno(giocatore.getNome()));

		if (dueGiocatori) {
			inviaEvento(new RichiestaPastore(), giocatore);
			SceltaPastore scelta = (SceltaPastore) gestoreEventi.aspetta(SceltaPastore.class);
			pastore = scelta.getPastore();
		} else {
			pastore = giocatore.getPastori().get(0);
		}

		for (int numMossa = 1; numMossa <= Costanti.NUM_MOSSE; numMossa++) {
			Set<TipoMossa> mosseDisponibili = gestoreMossa.creaMosseDisponibili(numMossa, pastoreMosso, mossaPrecedente, pastore, giocatore.getDenaro());

			inviaEvento(new RichiestaTipoMossa(mosseDisponibili, numMossa), giocatore);

			TipoMossa tipoMossa = ((SceltaMossa) gestoreEventi.aspetta(SceltaMossa.class)).getMossa();

			gestoreMossa.effettuaMossa(pastore, tipoMossa);
		}
	}

	private void movimentoPecoraNera() {
		int lancio = lanciaDado();

		Territorio origine = partita.getGregge().getPecoraNera().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);
		if (movimentoPossibile(origine, lancio)) {
			partita.getGregge().getPecoraNera().muoviIn(destinazione);
			connessione.inviaEvento(new MovimentoPecoraNera(origine.getCodice(), destinazione.getCodice()), tuttiGiocatori);
		}
	}

	public int lanciaDado() {
		int lancio = Sorte.lanciaDado();
		inviaEventoATutti(new LancioDado(lancio));
		return lancio;
	}

	private void movimentoLupo() {
		boolean tutteStradeOccupate = true;

		int lancio = lanciaDado();

		Territorio origine = partita.getLupo().getPosizione();
		Territorio destinazione = Mappa.getMappa().transizione(origine, lancio);

		for (int i = 0; i < 5; i++) {
			if (movimentoPossibile(origine, i)) {
				tutteStradeOccupate = false;
				break;
			}
		}

		if (movimentoPossibile(origine, lancio) || tutteStradeOccupate) {
			partita.getLupo().muoviIn(destinazione);
			inviaEventoATutti(new MovimentoLupo(origine.getCodice(), destinazione.getCodice()));
		}
	}

	private boolean movimentoPossibile(Territorio origine, int dado) {
		Territorio destinazione = Mappa.getMappa().transizione(origine, dado);
		if (!origine.equals(destinazione) && partita.stradaLibera(new Strada(origine, destinazione))) {
			return true;
		}
		return false;
	}

	private void market() {
		// TODO Auto-generated method stub

	}

	private boolean recintiInizialiFiniti() {
		return partita.getRecinti().getRecintiIniziali().size() == Costanti.NUM_RECINTI_INIZIALI;
	}

	private void selezionePosizioneIniziale() {
		giroPosizionamentoPastore(0);
		if (dueGiocatori) {
			giroPosizionamentoPastore(1);
		}
	}

	private void giroPosizionamentoPastore(int numPastore) {
		for (Giocatore g : partita.getGiocatori()) {
			boolean[] stradeLibere = Estrattore.stradeLibere(partita);
			inviaEvento(new RichiestaPosizioneInizialePastore(stradeLibere), g);
			PosizionamentoPastore risposta = (PosizionamentoPastore) gestoreEventi.aspetta(PosizionamentoPastore.class);

			Strada strada = Mappa.getMappa().getStrade()[risposta.getStrada()];
			g.getPastori().get(numPastore).muoviIn(strada);

			inviaEventoATutti(new PosizionamentoPastore(g.getNome(), risposta.getStrada()));
		}
	}

	protected void inviaEventoATutti(Evento e) {
		getConnessione().inviaEvento(e, tuttiGiocatori);
	}

	protected void inviaEvento(Evento e, Giocatore g) {
		getConnessione().inviaEvento(e, g.getNome());
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

	protected GestoreCoda<Evento> getGestoreEventi() {
		return gestoreEventi;
	}

	protected Partita getPartita() {
		return partita;
	}

	protected InterfacciaServer getConnessione() {
		return connessione;
	}

	protected List<String> getTuttiGiocatori() {
		return tuttiGiocatori;
	}

}
