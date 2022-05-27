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
				numGames.setText(number + "");
			}
		}
	}

	public void createGame(){
		games.add(new GameButton("./bounce/bounce.sh", "images/bounce.png")); //November 2020
		games.add(new GameButton("./space-game/space.sh", "images/space.png")); //December 2020
		games.add(new GameButton("./pogo-fish/pogofish.sh", "images/pogofish.png")); //January-March 2021
		games.add(new GameButton("./kivy-pong/kivypong.sh", "images/kivypong.png")); //April 2021
		games.add(new GameButton("./element-finder/finder.sh", "images/finder.png")); //April-May 2021
		games.add(new GameButton("./gomoku/gomoku.sh", "images/gomoku.png")); //October 2021
		games.add(new GameButton("./snake/snake.sh", "images/snake.png")); //November 2021
		games.add(new GameButton("./finger-dancer/dancer.sh", "images/dancer.png")); //December 2021
		setUpWindow();
	}

	public void setUpWindow(){
                JFrame frame = new JFrame("Lilja Arcade");
                frame.setLayout(null);

                for (int x = 0; x < games.size(); x++){
			int posY = (int) 185 + x/4*125;
			int posX = (int) x%4*125;

			games.get(x).setBounds(posX, posY, 150, 150);
        	        games.get(x).addActionListener(this);
                        games.get(x).setFocusPainted(false);
                        games.get(x).setMargin(new Insets(0, 0, 0, 0));
                        games.get(x).setContentAreaFilled(false);
                        games.get(x).setBorderPainted(false);
                        games.get(x).setOpaque(false);
			frame.add(games.get(x));
		}

		numGames = new JLabel(number + "");
		numGames.setForeground(Color.WHITE);
		numGames.setLocation(20, 0);
                numGames.setSize(500, 50);
		numGames.setFont(new Font("Press Start 2P", Font.PLAIN, 24));
		frame.add(numGames);

		JLabel bg = new JLabel(new ImageIcon("images/bg.png"));
                bg.setBounds(0, 0, 525, 600);
                frame.add(bg);

                frame.setSize(525, 625);
                frame.setIconImage(new ImageIcon("images/cover.png").getImage());
		frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
	}
}
