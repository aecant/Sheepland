package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class GestoreClientSocket extends Thread {
	private final PrintStream LOGGER = ServerMain.LOGGER;

	private Socket socket;
	private ConnessioneServerSocket connessione;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private AscoltatoreSocket<Integer> ascoltatoreMosse;
	private Utente utente;

	public GestoreClientSocket(Socket socket, ConnessioneServerSocket connessione) {
		this.socket = socket;
		this.connessione = connessione;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		registrazioneGiocatore();

		riceviMosse();
	}

	private void riceviMosse() {
		ascoltatoreMosse = new AscoltatoreSocket<Integer>(in, utente.getCodaMosse());
		ascoltatoreMosse.start();
	}

	public void inviaEvento(Evento evento) {
		try {
			out.reset();
			out.writeObject(evento);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registrazioneGiocatore() {
		try {
			Object oggettoRicevuto = in.readObject();
			if (!(oggettoRicevuto instanceof Coppia<?, ?>)) {
				throw new IOError(null);
			}
			@SuppressWarnings("unchecked")
			Coppia<String, String> nomeEPassword = (Coppia<String, String>) oggettoRicevuto;
			out.reset();
			if (ServerMain.aggiungiUtente(nomeEPassword.primo, nomeEPassword.secondo, connessione)) {
				out.writeObject(CostantiSocket.REGISTRAZIONE_OK);
				utente = ServerMain.getUtente(nomeEPassword.primo);
				connessione.getGestoriUtenti().put(utente, this);
				LOGGER.println(nomeEPassword.primo + " registrato");
			} else {
				out.writeObject(CostantiSocket.NOME_GIA_PRESENTE);
			}
			out.flush();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void terminaConnessione() {
		ascoltatoreMosse.ferma();
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
