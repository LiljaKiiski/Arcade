import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/* CLIENT CLASS
 * - Used to connect to server from Main Class
 * - Sends messages to ClientHandler Class
 */

public class Client {
	public final String IPADDRESS = "localhost"; //Change your IP Address here

	public Socket socket;
	public BufferedReader br;
	public BufferedWriter bw;
	public Main main;
   	public boolean connected = false;

	//Constructor
    	public Client(Main main) {
		this.main = main;
		try { 
                	socket = new Socket(IPADDRESS, 2021);
                	br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                	bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                	connected = true;
	       	} catch (IOException e) { }
	}

	//Sends message to ClientHandler Class
    	public void sendMessage(String message) {
		try {
    			bw.write(message);
			bw.newLine();
                	bw.flush();
                } catch (IOException e) { }
    	}
}
