# Sheepland
This was my final project for Bachelor's Degree in Computer Science and Engineering at Politecnico di Milano.
It was a team effort with [Alessandro Dignani](https://github.com/alessandrodignani).

[Sheepland](https://www.amazon.it/Cranio-Creations-CC012-Gioco-Sheepland/dp/B008JY9D7U) is a board game by Cranio Creations, and this project is a videogame transposition of the original game.
It is written in Java 7 and it's developed under client-server architecture.

This was our first OOP project and the code is written in Italian, we were young :)

## User guide
To play a game of Sheepland you must first start the server the MainServer class, and then start the client by running the MainClient class (both are contained in the controller package).

The server accepts clients that connect both with RMI and socket.

After starting the client, before starting the actual game, you must enter the IP address of the server (localhost is fine), known in advance, the preferred connection (RMI or socket) and the desired user interface (CLI or GUI).

When connecting to the server, a name and password are required.

Once at least two users have registered, a 15-second timer starts, after which the game begins. If a third player is added the timer starts again waiting for the fourth; if the players are four the game starts immediately.

The server can handle multiple games at the same time.

If the connection with the server is lost or if you close the client application you can reconnect using the same username and password pair, and you can go back to play the same game. If the reconnection is made within 30 seconds the game will restart from where it left off, after 30 seconds from disconnection the other players will continue in the game and the disconnected user will skip their turns until eventually they will reconnect.

The matches can take place with users who choose different connections and user interfaces. For example, you could play a game between a user connected via a socket using the CLI and a user connected via RMI using the GUI. A user can log off using RMI and reconnect via socket, and vice versa.

To play with the CLI, the user must have the Sheepland map in hand with the codes of the roads and the territory (the map is in the Documentation folder).