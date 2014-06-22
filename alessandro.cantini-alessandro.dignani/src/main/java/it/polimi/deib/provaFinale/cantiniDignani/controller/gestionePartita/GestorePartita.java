package it.polimi.deib.provaFinale.cantiniDignani.controller.gestionePartita;

import it.polimi.deib.provaFinale.cantiniDignani.controller.CostantiController;
import it.polimi.deib.provaFinale.cantiniDignani.controller.ElementoNonPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TimerDisconnessione;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.LancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Partita;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Sorte;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorePartita extends Thread {

	private final static Logger LOGGER = Logger.getLogger(GestorePartita.class.getName());

	private List<Utente> utenti;
	private final Partita partita;
	private final List<String> tuttiGiocatori;

	private final PreparazionePartita preparazionePartita;
	private final FaseIniziale faseIniziale;
	private final FasePrincipale fasePrincipale;
	private final FaseFinale faseFinale;

	private final TimerDisconnessione timer;
	
	public final boolean dueGiocatori;
	public final int denaroIniziale;

	public GestorePartita(List<Utente> utenti) {
		this.utenti = utenti;

		tuttiGiocatori = new ArrayList<String>();
		for (Utente u : utenti) {
			tuttiGiocatori.add(u.getNome());
		}

		partita = new Partita(tuttiGiocatori);

		if (partita.getGiocatori().size() == 2) {
			dueGiocatori = true;
			denaroIniziale = CostantiModel.DENARO_INIZIALE_DUE_GIOCATORI;
		} else {
			dueGiocatori = false;
			denaroIniziale = CostantiModel.DENARO_INIZIALE;
		}

		preparazionePartita = new PreparazionePartita(this);
		faseIniziale = new FaseIniziale(this);
		fasePrincipale = new FasePrincipale(this);
		faseFinale = new FaseFinale(this);

		timer = new TimerDisconnessione(CostantiController.MILLISECONDI_INTERRUZIONE_DISCONNESSIONE, this);
	}

	public void run() {

		preparazionePartita.esegui();

		faseIniziale.esegui();

		fasePrincipale.esegui();

		faseFinale.esegui();
	}

	protected int lanciaDado(MotivoLancioDado motivo) {
		int lancio = Sorte.lanciaDado();
		inviaEventoATutti(new LancioDado(lancio, motivo));
		return lancio;
	}

	protected int aspettaMossa(Giocatore giocatore) throws GiocatoreDisconnessoException {
		int mossa = getUtente(giocatore).getCodaMosse().aspetta();
		if (mossa == CostantiController.MOSSA_DISCONNESSIONE) {
			throw new GiocatoreDisconnessoException();
		}
		return mossa;
	}

	public void inviaEventoATutti(Evento evento) {
		for (Giocatore g : partita.getGiocatori()) {
			inviaEvento(evento, g);
		}
	}

	public void inviaEvento(Evento evento, Giocatore g) {
		Utente utente = getUtente(g);
		if (utente.getConnessione() != null) {
			utente.inviaEvento(evento);
		}
	}

	protected void pagamento(int somma, Giocatore pagante, Giocatore ricevente) {
		pagante.sottraiDenaro(somma);
		ricevente.aggiungiDenaro(somma);
	}

	public Partita getPartita() {
		return partita;
	}

	protected List<String> getTuttiGiocatori() {
		return tuttiGiocatori;
	}

	public PreparazionePartita getPreparazionePartita() {
		return preparazionePartita;
	}

	protected FaseIniziale getFaseIniziale() {
		return faseIniziale;
	}

	protected FasePrincipale getFasePrincipale() {
		return fasePrincipale;
	}

	protected FaseFinale getFaseFinale() {
		return faseFinale;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	protected boolean giocatoreOffline(Giocatore giocatore) {
		return getUtente(giocatore).getConnessione() == null;
	}

	private Utente getUtente(Giocatore giocatore) {
		for (Utente u : utenti) {
			if (u.getNome().equals(giocatore.getNome())) {
				return u;
			}
		}
		throw new ElementoNonPresenteException(giocatore + " non presente in " + utenti);
	}

	public synchronized void sospendiPartita() {
		try {
			synchronized(this) {
				LOGGER.info("Iniziato timer disconnessione, partita sospesa");
				timer.start();
				this.wait();
			}
			LOGGER.info("Partita ripresa");
			timer.termina();
		} catch (InterruptedException e) {
			LOGGER.log(Level.SEVERE, "gestore interrotto in modo inaspettato", e);
		}
	}

	public TimerDisconnessione getTimerDisconnessione() {
		return timer;
	}

}
