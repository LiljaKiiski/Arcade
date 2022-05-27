import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* GAME CLASS
 * - Handles each specific game
 * - Created from Server Class
 */

public class Game {
	public ClientHandler[] clients;
	public String[][] grid = new String[19][19];
	public Lock lock = new ReentrantLock();

	//Constructor
	public Game(ClientHandler[] clients){
		this.clients = clients;

		for (int x = 0; x < grid.length; x++){
			Arrays.fill(grid[x], "");
		}
	}

	//Starts game
	public void startGame(){
		clients[0].clients = clients;
                clients[1].clients = clients;

                clients[0].sendMessage("PLAYER");
                clients[0].sendMessage("black");
                clients[1].sendMessage("PLAYER");
                clients[1].sendMessage("white");

                clients[0].thread.start();
                clients[1].thread.start();

                clients[0].sendMessage("TURN");
	}
}
