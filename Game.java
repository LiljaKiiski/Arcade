import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;

public class Game {
	String command;
	Image image;

	public Game(String command, String imageName) {
		this.command = command;
		image = new ImageIcon(imageName).getImage();
	}

	public void execute(){
		try {
                        Runtime runTime = Runtime.getRuntime();
                        Process process = runTime.exec(command);
                } catch (IOException e) {
                        e.printStackTrace();
                }
	}
}
