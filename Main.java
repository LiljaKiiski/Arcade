import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main implements ActionListener{
	public ArrayList<GameButton> games = new ArrayList<>();

	public static void main (String[] args){
		new Main().createGame();
	}

	public void actionPerformed(ActionEvent e){
		for (int x = 0; x < games.size(); x++){
			if (e.getSource() == games.get(x)){
				games.get(x).execute();
			}
		}
	}

	public void createGame(){
		games.add(new GameButton("./Bounce/bounce.sh", "images/bounce.png"));
		games.add(new GameButton("./SpaceGame/space.sh", "images/space.png"));
		games.add(new GameButton("./Snake/snake.sh", "images/snake.png"));
		games.add(new GameButton("./FingerDancer/dancer.sh", "images/dancer.png"));
		setUpWindow();
	}

	public void setUpWindow(){
                JFrame frame = new JFrame("Lilja's Mini Games");
                frame.setLayout(null);

                for (int x = 0; x < games.size(); x++){
			games.get(x).setBounds(x*175+25, 200, 150, 150);
        	        games.get(x).addActionListener(this);
                        games.get(x).setFocusPainted(false);
                        games.get(x).setMargin(new Insets(0, 0, 0, 0));
                        games.get(x).setContentAreaFilled(false);
                        games.get(x).setBorderPainted(false);
                        games.get(x).setOpaque(false);
			frame.add(games.get(x));
		}

		JLabel bg = new JLabel(new ImageIcon("images/bg.png"));
                bg.setLocation(0, 0);
                bg.setSize(725, 700);
                frame.add(bg);

                frame.setSize(725, 700);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
	}
}
