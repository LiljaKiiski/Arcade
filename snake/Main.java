import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener, ActionListener{
	public ImageIcon[][] grid = new ImageIcon[15][15]; //Grid with image icons
	public Snake snake = new Snake(grid.length); //Main snake
	public Coordinate apple; //Apple coordinate
	public int numApples = 0; //Number of apples collected
	public boolean turnedThisRound = false; //Allow turn of snake once per cycle

	public static void main (String[] args) {
		new Main().setUpGame();
	}

	//Runs every 1/5 of a second
	public void actionPerformed(ActionEvent event){	
		//If snake alive
		if (checkSnakeAlive()){
			snake.move();
			turnedThisRound = false;
		}

		//If snake is still alive
		if (checkSnakeAlive()){
			drawGrid();

			int posX = snake.blocks.get(0).posX;
	                int posY = snake.blocks.get(0).posY;

                	//If snake and apple Collided
        	        if (posX == apple.posX && posY == apple.posY){
                        	numApples++;
				addApple();
				snake.addBlock();
			}
		}
		repaint();
                Toolkit.getDefaultToolkit().sync();
	}

	@Override
	//Paints screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;

		//Draw grid
		for (int x = 0; x < grid.length; x++){
                        for (int y = 0; y < grid[x].length; y++){
                        	g2d.drawImage(grid[x][y].getImage(), x*50, y*50, null);      
                        }
                }

		//Draws text
		g.setFont(new Font("Roboto", Font.PLAIN, 30)); 
		g2d.drawString("# of apples: " + Integer.toString(numApples), 10, 37);

		if (!checkSnakeAlive()){
			g.setFont(new Font("Roboto", Font.PLAIN, 100));
                	g2d.drawString("You Lose!", 150, 200);
		}					     
	}	

	//Listens to Keys
        public void keyPressed(KeyEvent event) {
		char dir = KeyEvent.getKeyText(event.getKeyCode()).toLowerCase().charAt(0);
               	if ((dir == 'u' || dir == 'd' || dir == 'l' || dir == 'r') && !turnedThisRound && checkSnakeAlive()){
                        snake.setDirection(dir);
        		turnedThisRound = true;
	        } 
        }

        public void keyReleased(KeyEvent event) { }

	public void keyTyped(KeyEvent event){ }

	//Adds stuff to grid
	public void drawGrid(){
		//Draw grass		
		for (int x = 0; x < grid.length; x++){
                        for (int y = 0; y < grid[x].length; y++){
                                int num = y%2 + x%2;
                                if (num > 1){
                                        num = 0;
                                }
                                grid[x][y] = new ImageIcon("images/g" + num + ".png");
                        }
                }
		
		//Draw apple
		grid[apple.posX][apple.posY] = new ImageIcon("images/a.png");

		//Draw Snake
                for (int x = 0; x < snake.blocks.size(); x++){
			try {
                		Coordinate block = snake.blocks.get(x);
                       		grid[block.posX][block.posY] = new ImageIcon("images/s.png");
               		} catch (ArrayIndexOutOfBoundsException e){
                	        snake.alive = false;
		        }

		}
	}

	//Changes apple pos
      	public void addApple(){
		apple.posX = (int)(Math.random() * grid.length-1);
		apple.posY = (int)(Math.random() * grid.length-1);
	}

	//Checks snake's life status
	public boolean checkSnakeAlive(){
		drawGrid();

		int numSnakes = 0;
		int posX = snake.blocks.get(0).posX;
                int posY = snake.blocks.get(0).posY;

		for (int x = 0; x < grid.length; x++){
                        for (int y = 0; y < grid[x].length; y++){
                                if (("" + grid[x][y]).substring(7, 8).equals("s")){
                                        numSnakes++;
                                }
                        }
                }

		//Check collision with self
		if (numSnakes != snake.blocks.size()){
			snake.alive = false;
			return false;
		}

		return snake.alive;
	}

	//Sets up game
	public void setUpGame(){
		grid = new ImageIcon[15][15];
                snake = new Snake(grid.length);
                apple = new Coordinate(0, 0);
                addApple();
                numApples = 0;
                drawGrid();
                snake.alive = true;
                snake.setDirection('r');
		
		JFrame f = new JFrame("Snake");
		f.setLayout(new GridLayout(1, 1));
		f.add(this);
		f.setIconImage(new ImageIcon("images/cover.png").getImage());
                f.addKeyListener(this);
                f.setFocusable(true);
                f.setSize(750, 787);
                f.setLocationRelativeTo(null);
                f.setResizable(false);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);

                Timer timer = new Timer(200, this);
                timer.start();
        }

}
