import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] zero) {

		ServerSocket socketserver  ;
		Socket socketduserveur ;

		try {

			socketserver = new ServerSocket(50001);
			while(true) {
				socketduserveur = socketserver.accept(); 
				System.out.println("Connexion réalisé");
				//		        socketserver.close();
				//		        socketduserveur.close();
			}

		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
