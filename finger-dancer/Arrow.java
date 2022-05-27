import java.awt.Image;
import javax.swing.ImageIcon;

//Arrow Class
public class Arrow { 
	String direction;
	Image image;
	int posX;
	int posY;
	int width;
	int height;

	//Constructor
	public Arrow(String direction, int posX, int posY){
		this.direction = direction;
		image = new ImageIcon("images/" + direction + ".png").getImage();
	
		this.posX = posX;
		this.posY = posY;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	//Checks collision between arrow and this arrow
        public boolean hasCollided(Arrow a) {
		//This arrow
                int cLeft = posX;
                int cRight = posX + width;
                int cTop = posY;
                int cBottom = posY + height;

		//Other arrow
               	int tLeft = a.posX;
                int tRight = a.posX + a.width;
                int tTop = a.posY;
                int tBottom = a.posY + a.height;

		//Checks collision with x and y axis
                if(((cLeft >= tLeft && cLeft <= tRight) ||
                                (cRight >= tLeft && cRight <= tRight) ||
                                (cLeft >= tLeft && cRight <= tRight) ||
                                (cLeft <= tLeft && cRight >= tRight)) &&

                                ((cTop >= tTop && cTop <= tBottom) ||  
                                                (cBottom <= tBottom && cBottom >= tTop) ||
                                                (cTop >= tBottom && cBottom <= tBottom ) ||
                                                (cTop <= tBottom && cBottom >= tBottom))) {
                        return true;
                }
                return false;
        }
}
