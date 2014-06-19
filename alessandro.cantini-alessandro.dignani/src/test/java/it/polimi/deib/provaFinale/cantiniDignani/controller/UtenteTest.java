package it.polimi.deib.provaFinale.cantiniDignani.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtenteTest {

	Utente utente;
	final String nome = "nome esempio";
	final String password = "pass esempio";
	final int mossa = 2;

	@Before
	public void setUp() throws Exception {
		utente = new Utente(nome, password, null);
	}

	@Test
	public void testUtente() {
		assertTrue(utente.getNome().equals(nome));
		assertTrue(utente.getPassword().equals(password));

		utente.getCodaMosse().aggiungi(mossa);
		assertTrue(utente.getCodaMosse().aspetta() == mossa);
	}

}
