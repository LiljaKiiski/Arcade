import java.io.*;
import javax.sound.sampled.*;

//SoundPlayer Class
public class SoundPlayer{
	public Clip clip;
	public AudioInputStream audioStream;
	
	//Constructor
	public SoundPlayer(String file_name) {
		try {
			File file = new File(file_name);
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}	
	}
	
	//Play music
	public void play() {
		 clip.start();
	}
	
	//Pause music 
	public void pause() {
		 clip.stop();
	}
	
	//Restart music
	public void reset() {
		 clip.setMicrosecondPosition(0);
	}
}
