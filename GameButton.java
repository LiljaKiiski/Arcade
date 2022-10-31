import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.io.IOException;

public class GameButton extends JButton {
	String command;

	public GameButton(String command, String imageName) {
		this.command = command;
		setIcon(new ImageIcon(imageName));
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