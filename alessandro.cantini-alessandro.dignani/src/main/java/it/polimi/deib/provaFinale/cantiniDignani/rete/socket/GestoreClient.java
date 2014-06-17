package it.polimi.deib.provaFinale.cantiniDignani.rete.socket;

import it.polimi.deib.provaFinale.cantiniDignani.controller.ServerMain;
import it.polimi.deib.provaFinale.cantiniDignani.controller.Utente;
import it.polimi.deib.provaFinale.cantiniDignani.controller.eventi.Evento;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class GestoreClient extends Thread {
	private final PrintStream LOGGER = ServerMain.LOGGER;

	private Socket socket;
	private ConnessioneServerSocket connessione;
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private AscoltatoreSocket<Integer> ascoltatoreMosse;
	private Utente utente;

	public GestoreClient(Socket socket, ConnessioneServerSocket connessione) {
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
			Object oggettoNome = in.readObject();
			if (!(oggettoNome instanceof String)) {
				throw new IOError(null);
			}
			String nome = (String) oggettoNome;
			out.reset();
			if (ServerMain.aggiungiUtente(nome, connessione)) {
				out.writeObject(CostantiSocket.REGISTRAZIONE_OK);
				utente = ServerMain.getUtente(nome);
				connessione.getGestoriUtenti().put(utente, this);
				LOGGER.println(nome + " registrato");
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
