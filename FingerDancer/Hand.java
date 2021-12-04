import java.awt.Image;
import javax.swing.ImageIcon;

//Hand Class
public class Hand {
	int posX;
	int posY;
	int place = 0;
	Image image;

	ImageIcon[] pics = new ImageIcon[] {new ImageIcon("images/hand1.png"), 
					new ImageIcon("images/hand2.png"),
					new ImageIcon("images/hand3.png"), 
					new ImageIcon("images/hand4.png"), 
					new ImageIcon("images/hand5.png")};

	//Hand Class
	public Hand(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
		image = pics[0].getImage();
	}

	//Animate hand
        public void animate() {
               	image = pics[place].getImage();

                place++;
                if (place > 4){
                        place = 0;
                }
        }





}
