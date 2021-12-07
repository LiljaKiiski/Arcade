import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main implements ActionListener{
	public ArrayList<GameButton> games = new ArrayList<>();
	public JLabel numGames;
	public int number = 0;

	public static void main (String[] args){
		new Main().createGame();
	}

	public void actionPerformed(ActionEvent e){
		for (int x = 0; x < games.size(); x++){
			if (e.getSource() == games.get(x)){
				games.get(x).execute();
				number++;
				numGames.setText("Games Played: " + number);
			}
		}
	}

	public void createGame(){
		games.add(new GameButton("./Bounce/bounce.sh", "images/bounce.png"));
		games.add(new GameButton("./SpaceGame/space.sh", "images/space.png"));
		games.add(new GameButton("./Snake/snake.sh", "images/snake.png"));
		games.add(new GameButton("./FingerDancer/dancer.sh", "images/dancer.png"));
		games.add(new GameButton("./ElementFinder/finder.sh", "images/finder.png"));
		setUpWindow();
	}

	public void setUpWindow(){
                JFrame frame = new JFrame("Lilja's Mini Games");
                frame.setLayout(null);

                for (int x = 0; x < games.size(); x++){
			int posY = (int) 200 + x/4*200;
			int posX = (int) x%4*175 + 25;

			games.get(x).setBounds(posX, posY, 150, 150);
        	        games.get(x).addActionListener(this);
                        games.get(x).setFocusPainted(false);
                        games.get(x).setMargin(new Insets(0, 0, 0, 0));
                        games.get(x).setContentAreaFilled(false);
                        games.get(x).setBorderPainted(false);
                        games.get(x).setOpaque(false);
			frame.add(games.get(x));
		}

		numGames = new JLabel("Games Played: " + number);
		numGames.setLocation(20, 0);
                numGames.setSize(200, 50);
		numGames.setFont(new Font("Roboto", Font.PLAIN, 24));
		frame.add(numGames);

                frame.setSize(725, 700);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
	}
}
