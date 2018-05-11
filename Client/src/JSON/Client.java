package JSON;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/** Le processus client se connecte au site fourni dans la commande
 *   d'appel en premier argument et utilise le port distant 8080.
 */
public class Client extends JFrame {
	 static Socket s;
	 
	 public static void Envoyer_Json() throws UnknownHostException, IOException {
		 Socket sock = general.Administration.sock;
		 if(sock !=null && sock.isClosed()) {
				sock = new Socket(InetAddress.getLocalHost(),9003);

		 }
		// sock = new Socket(InetAddress.getLocalHost(),9002);
		 Commun.transfert( new FileInputStream("json.json"), sock.getOutputStream(), true); 
		 sock.close(); 
	 }
	
	 
}