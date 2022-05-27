import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* CLIENTHANDLER CLASS
 * - Handles one client for Game Class
 * - Communicates between Main Class and Game Class
 * - Checks for game win
 */

public class ClientHandler implements Runnable{
	private Socket socket;
	public BufferedReader br;
	public BufferedWriter bw;

	public Thread thread;
	public Game game;
	public String player;
	public ClientHandler[] clients;

	//Constructor
	public ClientHandler(String player, Game game, Socket socket) throws IOException{
		this.socket = socket;
		this.game = game;
		this.player = player;

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		thread = new Thread(this);
	}

	@Override
	//Thread that runs ClientHandler for one player
	public void run() {
		try {
			while (true) {
				String msgFromClient = br.readLine();
				
				//Disconnected from game
				if (msgFromClient == null) {
					break;
				}

				//Turn finished
				if (msgFromClient.equals("TURN DONE")){
					int posX = Integer.parseInt(br.readLine());
					int posY = Integer.parseInt(br.readLine());

					//Update game grid
					try {
						game.lock.lock();
						game.grid[posX][posY] = player;
					} finally {
						game.lock.unlock();
					}

					sendMessageToOther("UPDATE GRID");
					sendMessage("UPDATE GRID");
					
					//Send entire grid to both players
					for (int x = 0; x < game.grid.length; x++){
						for (int y = 0; y < game.grid[x].length; y++){
							sendMessage(game.grid[x][y]);
							sendMessageToOther(game.grid[x][y]);
						}
					}

					//Send recently changed square
					sendMessage(String.valueOf(posX));
					sendMessage(String.valueOf(posY));
					sendMessage(player);

					sendMessageToOther(String.valueOf(posX));
					sendMessageToOther(String.valueOf(posY));
					sendMessageToOther(player);

					//Game over - win
					if (checkWin()){
						sendMessage("RESULTS");
						sendMessage(player);

						sendMessageToOther("RESULTS");
						sendMessageToOther(player);

					} //Game over - draw
					else if (checkDraw()) {
						sendMessage("RESULTS");
                                                sendMessage("draw");

                                                sendMessageToOther("RESULTS");
                                                sendMessageToOther("draw");
					
					} //Game continues
					else {
						sendMessageToOther("TURN");
					}
				}

			}
			sendMessageToOther("OTHER PLAYER DISCONNECTED");
			socket.close();
			br.close();
			bw.close();
		//Connection reset	
		} catch (IOException e) { }
	}

	//Sends message to player
	public void sendMessage(String message){
		try {
			bw.write(message);
	                bw.newLine();
        	        bw.flush();
		//Stream closed
		} catch (IOException e){ }
	}

	//Sends message to other player
	public void sendMessageToOther(String message){
		for (int x = 0; x < clients.length; x++){
			if (clients[x] != this){
				clients[x].sendMessage(message);

			}
		}
	}

	//Checks for draw
	public boolean checkDraw(){
		String[][] g = game.grid;

		for (int x = 0; x < g.length; x++){
			for (int y = 0; y < g[x].length; y++){
				if (g[x][y].equals("")){
					return false;
				}
			}
		}
		return true;
	}

	//Checks if player has won
	public boolean checkWin(){
		String[][] g = game.grid;

		for (int x = 0; x < g.length; x++){
			for (int y = 0; y < g.length; y++){
				//Column
				if (y+4 < 19 && g[x][y] == player && 
					g[x][y+1] == player && 
					g[x][y+2] == player && 
					g[x][y+3] == player &&
					g[x][y+4] == player){ 

					return true;
				}
				//Row
				if (x+4 < 19 && g[x][y] == player &&
                                	g[x+1][y] == player &&
                                        g[x+2][y] == player &&
                                        g[x+3][y] == player &&
                                        g[x+4][y] == player){

                                        return true;
                                }
				//Diagonal up
				if (x+4 < 19 && y+4 < 19 &&
					g[x][y] == player &&
                                        g[x+1][y+1] == player &&
                                        g[x+2][y+2] == player &&
                                        g[x+3][y+3] == player &&
                                        g[x+4][y+4] == player){

                                        return true;
				}
				//Diagonal down
				if (x+4 < 19 && y-4 > 0 &&
					g[x][y] == player &&
                                        g[x+1][y-1] == player &&
                                        g[x+2][y-2] == player &&
                                        g[x+3][y-3] == player &&
                                        g[x+4][y-4] == player){

                                        return true;
                                }
			}
		}
		return false;
	}
}
