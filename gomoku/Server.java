import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* SERVER CLASS
 * - Runs until terminates
 * - Creates new games for each next two players
 * - Each game is a new Game from Game Class
 */

public class Server {
	private ArrayList<Game> games = new ArrayList<>();

	//Runs server
	public static void main(String[] args) throws IOException, InterruptedException{
		Server server = new Server();
	}

	//Constructor
	public Server() throws IOException, InterruptedException{
		ServerSocket listener = new ServerSocket(2021);

		while (true) {
			Game game = new Game(new ClientHandler[2]);
                	Socket socket = listener.accept();

			//First player is black
                	game.clients[0] = new ClientHandler("black", game, socket);
  
			//Second player is white
			socket = listener.accept();
			game.clients[1] = new ClientHandler("white", game, socket);
		      	game.startGame();

			//Add game to running games
               		games.add(game);
		}
	}
}
