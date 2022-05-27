import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.IOException;

/* MAIN CLASS
 * - Runs game for one player
 * - Uses Client Class to connect to Server Class
 */

public class Main implements ActionListener {	
	public JButton[][] grid = new JButton[19][19];
	public JFrame frame;
	public Client client;
	public String player = "";
	public boolean oneMoveDone = false;

	//Runs game
	public static void main(String[] args) {
		new Main().setUpGame();	
	}

	@Override
	//Listenes for grid buttons to be pressed
	public void actionPerformed(ActionEvent e) {
		for (int x = 0; x < grid.length; x++){
                        for (int y = 0; y < grid[x].length; y++){
				String one  = "" + (new JButton(new ImageIcon("images/_tile.png"))).getIcon();
				String two = "" + grid[x][y].getIcon();
                                if (e.getSource() == grid[x][y] && one.equals(two)) {
					removeButtonListeners();
					client.sendMessage("TURN DONE");
					client.sendMessage(String.valueOf(x));
					client.sendMessage(String.valueOf(y));
				}
                        }
                }
	}

	//Sets up game
	public void setUpGame() {
		//Creates new client
		client = new Client(this);
		createWindow();

		if (!client.connected){
			frame.setTitle("You are not connected to the server");		
		} else {
			frame.setTitle("Waiting for second player to join");
			receiveMessages();
		}
	}

	//Receives messages sent from ClientHandler Class
	public void receiveMessages() {
		while (true){
			try {
				String message = client.br.readLine();

				//Disconnected from server
				if (message == null){
					frame.setTitle("You have disconnected from the server");
					break;
				}

				//Update player information
				if (message.equals("PLAYER")){
					player = client.br.readLine();

					if (player.equals("white")){
						frame.setTitle("Please wait for your turn...");
					}
					frame.setIconImage(new ImageIcon("images/" + player +  "_tile.png").getImage());
					oneMoveDone = true;
				}

				//Update grid
				if (message.equals("UPDATE GRID")){
                                        for (int x = 0; x < grid.length; x++){
                                                for (int y = 0; y < grid[x].length; y++){
							grid[x][y].setIcon(new ImageIcon("images/" + client.br.readLine() + "_tile.png"));
                                                }
                                        }

					//Recently changed square
					int posX = Integer.parseInt(client.br.readLine());
					int posY = Integer.parseInt(client.br.readLine());
					grid[posX][posY].setIcon(new ImageIcon("images/" + client.br.readLine() + "_tile2.png"));

					frame.setTitle("Please wait for your turn...");
					oneMoveDone = false;
				}

				//Player's turn
				if (message.equals("TURN")){	
					frame.setTitle("Your turn! - you are player " + player);
					addButtonListeners();
					oneMoveDone = false;					
				}

				//Game over - results
				if (message.equals("RESULTS")){
					String result = client.br.readLine();

					if (result.equals(player)){
						frame.setTitle("You win! - you got five in a row");
					} else if (result.equals("draw")){
						frame.setTitle("Draw - game board has been filled");
					} else {
						frame.setTitle("You lost! - other player got five in a row");
					}
					oneMoveDone = false;
					break;
				}

				//Game over - other disconnected
				if (message.equals("OTHER PLAYER DISCONNECTED")){

					//Game was abandoned before moves were played
					if (oneMoveDone){
						frame.setTitle("This game was abandoned - please play another game");

					} //Game was abandoned during game 
					else {
						frame.setTitle("You win! - other player disconnected");	
					}	
				}

			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	//Creates visual JFrame Window
	public void createWindow() {
		frame = new JFrame("Gomoku");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(19, 19));
		
		for (int x = 0; x < grid.length; x++){
                       for (int y = 0; y < grid[x].length; y++){
			       	grid[x][y] = new JButton(new ImageIcon("images/_tile.png"));
			       	grid[x][y].setFocusPainted(false);
			       	grid[x][y].setMargin(new Insets(0, 0, 0, 0));
				grid[x][y].setContentAreaFilled(false);
        			grid[x][y].setBorderPainted(false);
        			grid[x][y].setOpaque(false);
                               	panel.add(grid[x][y]);
		       }
                }

		frame.add(panel);
		frame.setIconImage(new ImageIcon("images/_tile.png").getImage());
                frame.setSize(655, 655);
		frame.setLocationRelativeTo(null);
               	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	//Removes button listeners from grid
	public void removeButtonListeners(){ //When not users turn, remove listeners so buttons can't be clicked
		for (int x = 0; x < grid.length; x++){
			for (int y = 0; y < grid[x].length; y++){
				grid[x][y].removeActionListener(this);
			}
		}
	}

	//Adds button listeners to grid
	public void addButtonListeners(){ //add button listeners
		for (int x = 0; x < grid.length; x++){
                        for (int y = 0; y < grid[x].length; y++){
                                grid[x][y].addActionListener(this);
                        }
                }

	}
}
