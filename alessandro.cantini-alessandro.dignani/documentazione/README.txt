Per giocare una partita a Sheepland si deve avviare prima il main del server contenuto nella classe MainServer, e successivamente avviare i client eseguendo la classe MainClient.

Il server accetta indifferentemente client che si connettono con RMI o socket.

Dopo aver avviato il client, prima dell’inizio della partita vera e propria, si deve inserire l’indirizzo IP del server, noto a priori, la connessione preferita (RMI o socket) e l’interfaccia utente desiderata (CLI o GUI). 

Al momento della connessione al server vengono richiesti un nome e una password. Non ci possono essere più utenti con lo stesso nome nella stessa sessione. 

Una volta che si sono registrati almeno due utenti inizia un timer di 15 secondi, scaduto il quale inizia la partita. Se si aggiunge un terzo giocatore il timer riparte in attesa del quarto; se i giocatori sono quattro la partita inizia subito.

Il server è in grado di gestire più partite contemporaneamente.

Se la connessione con il server viene persa o se si chiude l’applicazione del client è possibile riconnettersi utilizzando la stessa coppia di username e password, e si può tornare a giocare la stessa partita. Se la riconnessione viene effettuata entro 30 secondi la partita ricomincerà da dove si era interrotta, dopo 30 secondi dalla disconnessione gli altri giocatori continueranno nel gioco e l’utente disconnesso salterà i propri turni finché eventualmente non si riconnetterà.

Le partita possono svolgersi con utenti che scelgono connessioni e interfacce utente diverse. Per esempio si potrebbe giocare una partita fra un utente connesso via socket che usa la CLI e un utente connesso via RMI che usa la GUI. Un utente si può disconnettere usando RMI e riconnetere via socket, e viceversa.

Per giocare con la CLI l’utente deve avere sotto mano la mappa di Sheepland con i codici delle strade e dei territorio (la mappa è presente