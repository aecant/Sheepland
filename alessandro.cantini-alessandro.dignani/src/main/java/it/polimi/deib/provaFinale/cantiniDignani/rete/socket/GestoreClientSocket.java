package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerSheepland;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.rete.NomeGiaPresenteException;
import it.polimi.deib.provaFinale.cantiniDignani.rete.PasswordSbagliataException;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestoreClientSocket extends Thread {

	private final static Logger logger = Logger.getLogger(GestoreClientSocket.class.getName());

	private ConnessioneServerSocket connessione;
	private ServerSheepland serverSheepland;

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private AscoltatoreSocketServer ascoltatoreMosse;
	private Utente utente;

	private boolean registrato = false;

	public GestoreClientSocket(Socket socket, ConnessioneServerSocket connessione, ServerSheepland serverSheepland) {
		this.connessione = connessione;
		this.serverSheepland = serverSheepland;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Problemi nel creare gli object stream dal socket", e);
		}
	}

	public void run() {
		registrazioneGiocatore();

		if (registrato) {
			riceviMosse();
		}
	}

	private void riceviMosse() {
		ascoltatoreMosse = new AscoltatoreSocketServer(in, utente, serverSheepland);
		ascoltatoreMosse.start();
	}

	public void inviaEvento(Evento evento) {
		try {
			out.reset();
			out.writeObject(evento);
			out.flush();
		} catch (IOException e) {
			serverSheepland.gestisciDisconnessione(utente);
		}
	}

	private void registrazioneGiocatore() {
		while (!registrato) {
			try {
				Object oggettoRicevuto = in.readObject();

				@SuppressWarnings("unchecked")
				Coppia<String, String> nomeEPassword = (Coppia<String, String>) oggettoRicevuto;

				out.reset();
				try {
					serverSheepland.aggiungiUtente(nomeEPassword.primo, nomeEPassword.secondo, connessione);
					out.writeObject(CostantiSocket.REGISTRAZIONE_OK);
					utente = serverSheepland.getUtente(nomeEPassword.primo);
					connessione.getGestoriUtenti().put(utente, this);
					registrato = true;
				} catch (NomeGiaPresenteException e) {
					out.writeObject(CostantiSocket.NOME_GIA_PRESENTE);
				} catch (PasswordSbagliataException e) {
					out.writeObject(CostantiSocket.PASSWORD_SBAGLIATA);
				}
				out.flush();

			} catch (ClassNotFoundException e) {
				logger.log(Level.SEVERE, "problemi nella ricezione dell'evento", e);
			} catch (IOException e) {
				serverSheepland.gestisciDisconnessione(utente);
			}
		}
	}

	public void terminaConnessione() {
		if (ascoltatoreMosse.isAlive()) {
			ascoltatoreMosse.ferma();
		}

	}

}
