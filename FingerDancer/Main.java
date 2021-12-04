import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener, KeyListener{
	ArrayList<Arrow> arrows = new ArrayList<>();
	Arrow exArrow = new Arrow("GreyArrows", 0, 0);
	Hand hand = new Hand(450, 200);
        SoundPlayer player = new SoundPlayer("tetris.wav");
        JFrame frame;

	Timer addArrow = new Timer(450, this);
        Timer timer = new Timer(50, this);
        
	boolean gameOver = false;
	int score = 0;
	double speed = 13;
	int paintNum = 0;

	public static void main(String[] args){
		new Main().setUpGame();
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == addArrow){
			addArrow();
			speed += 0.2;
		} else if (e.getSource() == timer){
			Toolkit.getDefaultToolkit().sync();
                	repaint();
                	frame.setIconImage(hand.image);
		}
	}

        public void keyPressed(KeyEvent event) {	
		//If grey arrows have collided with moving arrow
		for (int x = 0; x < arrows.size(); x++){
			if (exArrow.hasCollided(arrows.get(x))){
				//If press corresponds to colliding arrow
				if (KeyEvent.getKeyText(event.getKeyCode()).equals(arrows.get(x).direction)){
                        		arrows.remove(arrows.get(x));
					score++;
                		}
				break;
			}
		}	
	}

        public void keyReleased(KeyEvent event) { }
        public void keyTyped(KeyEvent event){ }

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
                Graphics2D g2d = (Graphics2D)g;
	
        	g2d.drawImage(new ImageIcon("images/background.png").getImage(), 0, 0, null);
                g2d.drawImage(exArrow.image, exArrow.posX, exArrow.posY, null);
	
                g2d.drawImage(hand.image, hand.posX, hand.posY, null);
               	g2d.drawImage(new ImageIcon("images/board.png").getImage(), 630, 20, null);

                //Draws text
		g2d.setColor(Color.WHITE);
                g.setFont(new Font("Roboto", Font.PLAIN, 60));
                g2d.drawString(Integer.toString(score), 645, 80);

		//Arrows
                for (int x = 0; x < arrows.size(); x++){
                       	Arrow arrow = arrows.get(x);
                        g2d.drawImage(arrow.image, arrow.posX, arrow.posY, null);
                
			if (!gameOver){
				arrows.get(x).posY -= (int)speed;
			}

                        //Game is over
                        if (arrow.posY + 75 < 0){
                                gameOver = true;
                                player.pause();
                        	addArrow.stop();
				frame.removeKeyListener(this);
                	}
                }

		if (!gameOver) {		
			//Only animate hand every seventh rotation
			paintNum++;
			if (paintNum == 7){
				hand.animate();
				paintNum = 0;
			}

		} else {
			g.setFont(new Font("Roboto", Font.PLAIN, 150));
                        g2d.drawString("You Lose!", 50, 300);
		}
	}	

	public void addArrow(){
		String[] possibles = new String[] {"Left", "Down", "Up", "Right"};
                int random = (int)(Math.random() * 4);
		arrows.add(new Arrow(possibles[random], random*100, 600));
	}

        public void setUpGame() {
                frame = new JFrame("Finger Dancer");
        	frame.setLayout(new GridLayout(1, 1));
   		frame.add(this);

		frame.addKeyListener(this);
                frame.setFocusable(true);

                frame.setIconImage(hand.image);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
        	
		timer.start();
		addArrow.start();
		player.play();

		addArrow();
	}
}
