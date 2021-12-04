import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener{
	public ArrayList <Game> games = new ArrayList<>();

	public static void main (String[] args){
		new Main().createGame();
	}

	public void actionPerformed(ActionEvent e){

	}

	public void createGame(){
		games.add(new Game("./Bounce/bounce.sh", "images/bounce.png"));
		games.add(new Game("./SpaceGame/space.sh", "images/space.png"));
		games.add(new Game("./Snake/snake.sh", "images/snake.png"));
		games.add(new Game("./FingerDancer/dancer.sh", "images/dancer.png"));
		setUpWindow();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;

                //g2d.drawImage(level.bg.image, level.bg.posX, 0, null);
	}

	public void setUpWindow(){
		JFrame frame = new JFrame("Lilja's Mini Games");
		frame.setLayout(new GridLayout(1, 1));

		frame.add(this);
                frame.setSize(700, 700);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
	}
}
