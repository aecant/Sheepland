package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.controller.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;
import it.polimi.deib.provaFinale.cantiniDignani.controller.TipoMossa;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.util.Collection;
import java.util.List;

public interface InterfacciaUtente {

	/**
	 * L'utente deve scegliere il nome con cui giochera' la partita
	 * 
	 * @return il nome scelto dal giocatore
	 */
	String chiediNome();

	/**
	 * L'utente viene avvisato che il nome inserito in precedenza non va bene
	 */
	void nomeGiaPresente();

	/**
	 * Viene visualizzato l'inizio della partita e viene fatta vedere all'utente
	 * la sua tessera iniziale
	 */
	void inizioPartita();

	/**
	 * L'utente viene avvisato che e' iniziato il turno di un giocatore
	 * 
	 * @param giocatore
	 *            il giocatore che inizia il turno
	 */
	void inizioTurno(String giocatore);

	/**
	 * Viene lanciato un dado per un certo motivo
	 * 
	 * @param numero
	 *            il numero uscito
	 * @param motivo
	 *            il motivo per cui il dado e' stato lanciato
	 */
	void lancioDado(Integer numero, MotivoLancioDado motivo);

	/**
	 * Viene presentata la scelta della posizione iniziale di un pastore
	 * 
	 * @param giocatore
	 *            il giocatore che ha effettuato la scelta
	 * @param strada
	 *            la strada su cui viene posizionato il pastore
	 */
	void selezionePosizioneInizialePastore(String giocatore, int strada);

	/**
	 * Viene presentato lo spostamento di una pecora
	 * 
	 * @param giocatore
	 *            il giocatore che effettua lo spostamento
	 * @param pecora
	 *            la pecora spostata
	 * @param origine
	 *            il codice del territorio da cui parte la pecora
	 * @param destinazione
	 *            il codice del territorio in cui arriva la pecora
	 */
	void movimentoPecora(String giocatore, TipoAnimale pecora, int origine, int destinazione);

	/**
	 * Viene presentato lo spostamento della pecora nera
	 * 
	 * @param origine
	 *            il codice del territorio da cui parte la pecora nera
	 * @param destinazione
	 *            il codice del territorio in cui arriva la pecora nera
	 */
	void movimentoPecoraNera(int origine, int destinazione);

	/**
	 * Viene presentato lo spostamento di un pastore
	 * 
	 * @param giocatore
	 *            il giocatore che ha spostato il pastore
	 * @param origine
	 *            il codice della strada da cui si muove il pastore
	 * @param destinazione
	 *            il codice della strada in cui arriva il pastore
	 */
	void movimentoPastore(String giocatore, int origine, int destinazione);

	/**
	 * Viene presentato il movimento del lupo
	 * 
	 * @param origine
	 *            il codice del territorio da cui parte il lupo
	 * @param destinazione
	 *            il codice del territorio in cui arriva il lupo
	 */
	void movimentoLupo(int origine, int destinazione);

	/**
	 * Viene presentato l'acquisto di una tessera da parte di un giocatore
	 * 
	 * @param giocatore
	 *            il giocatore che ha effettuato l'acquisto
	 * @param tessera
	 *            la tessera comprata dal giocatore
	 */
	void acquistoTessera(String giocatore, Tessera tessera);

	/**
	 * Viene presentato l'eventuale abbattimento di una pecora. Se
	 * l'abbattimento non e' andato a buon fine l'utente viene notificato del
	 * mancato successo.
	 * 
	 * @param giocatore
	 *            il giocatore che ha effettuato il tentativo di abbattimento
	 * @param tipo
	 *            il tipo di ovino che e' stato coinvolto
	 * @param territorio
	 *            il codice del territorio in cui e' stato effettuato il
	 *            tentativo
	 * @param aBuonFine
	 *            se l'abbattimento si e' effettivamente verificato oppure no
	 */
	void abbattimento(String giocatore, TipoAnimale tipo, int territorio, boolean aBuonFine);

	/**
	 * Viene presentato l'eventuale accoppiamento fra una pecora e un montone.
	 * Se l'accoppiamento non e' andato a buon fine l'utente viene notificato
	 * del mancato successo.
	 * 
	 * @param giocatore
	 *            il giocatore che ha effettuato il tentativo di accoppiamento
	 * @param territorio
	 *            il codice del territorio in cui e' stato effettuato il
	 *            tentativo
	 * @param aBuonFine
	 *            se l'abbattimento si e' effettivamente verificato oppure no
	 */
	void accoppiamento(String giocatore, int territorio, boolean aBuonFine);

	/**
	 * Un agnello si e' trasformato in una pecora o in un montone
	 * 
	 * @param maschio
	 *            true se e' diventato un montone, false se e' diventato una
	 *            pecora
	 * @param territorio
	 *            il territorio in cui l'agnello si e' trasformato
	 */
	void trasformazioneAgnello(boolean maschio, Integer territorio);

	/**
	 * Viene presentato il pagamento di una somma di denaro da un giocatore a un
	 * altro.
	 * 
	 * @param denaro
	 *            la somma di denaro pagata
	 * @param pagante
	 *            il giocatore che effettua il pagamento
	 * @param pagato
	 *            il giocatore che riceve il pagamento
	 */
	void pagamento(Integer denaro, String pagante, String pagato);

	/**
	 * Viene presentata l'uccisione di un ovino da parte del lupo
	 * 
	 * @param territorio
	 *            il territorio in cui l'ovino e' stato ucciso
	 * @param tipoUcciso
	 *            il tipo dell'ovino ucciso
	 */
	void uccisioneLupo(int territorio, TipoAnimale tipoOvino);

	/**
	 * L'utente sceglie la strada su cui posizionare il pastore all'inizio della
	 * partita
	 * 
	 * @param stradeLibere
	 *            l'array delle strade libere
	 * @return il codice della strada su cui si vuole posizionare il pastore
	 */
	int richiestaPosizioneInizialePastore(boolean[] stradeLibere);

	/**
	 * L'utente scegle la strada su cui muovere il pastore
	 * 
	 * @param stradeLibereGratis
	 *            le strade libere vicine
	 * @param stradeLibereAPagamento
	 *            le strade libere per cui bisogna pagare
	 * @return il codice della strada su cui si vuole muovere il pastore
	 */
	int richiestaPosizionePastore(boolean[] stradeLibereGratis, boolean[] stradeLibereAPagamento);

	/**
	 * Restituisce il codice della strada del pastore scelto
	 * 
	 * @return il codice della strada del pastore scelto
	 */
	int richiestaPastore();

	/**
	 * L'utente sceglie un territorio in cui far accoppiare una pecora e un
	 * montone
	 * 
	 * @param territoriDisponibili
	 *            i territori disponibili per l'accoppiamento
	 * @return il codice del territorio scelto
	 */
	int richiestaTerritorioPerAccoppiamento(Collection<Integer> territoriDisponibili);

	/**
	 * L'utente sceglie la mossa da effettuare
	 * 
	 * @param mosseDisponibili
	 * @param numMossa
	 *            il numero della mossa
	 * @return l'indice della mossa scelta all'interno della lista delle mosse
	 *         disponibili
	 */
	int richiestaTipoMossa(List<TipoMossa> mosseDisponibili, int numMossa);

	/**
	 * L'utente sceglie la pecora da muovere
	 * 
	 * @param oviniSpostabili
	 *            la lista delle pecore che si possono muovere
	 * @return l'indice della pecora scelta all'interno della lista passata come
	 *         parametro
	 */
	int richiestaPecoraDaMuovere(List<Coppia<Integer, TipoAnimale>> oviniSpostabili);

	/**
	 * L'utente sceglie una pecora da abbattere
	 * 
	 * @param oviniAbbattibili
	 *            la lista delle pecore che si possono abbattere
	 * @return l'indice della pecora scelta all'interno della lista passata come
	 *         parametro
	 */
	int richiestaPecoraDaAbbattere(List<Coppia<Integer, TipoAnimale>> oviniAbbattibili);

	/**
	 * L'utente sceglie una tessera da acquistare
	 * 
	 * @param tessereDisponibili
	 *            le tessere acquistabili
	 * @return l'indice della tessera scelta all'interno della lista passata
	 *         come parametro
	 */
	int richiestaTesseraDaAcquistare(List<Tessera> tessereDisponibili);

}
