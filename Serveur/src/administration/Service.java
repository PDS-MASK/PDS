package administration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

class Service extends Thread {
	Socket socket;
	

	Service(Socket socket) throws IOException {
		this.socket = socket;
		this.start();
		
			
	}

	public void run() {
		try {
			if(socket.isClosed()) {
				   socket =Serveur.sv.accept();

			}
			JSON.Commun.transfert( socket.getInputStream(), new FileOutputStream("json.json"), true);
			  socket.close();
			  String action_bouton = LecteurJSON.Get_Action()[0];
			  String action_page = LecteurJSON.Get_Action()[1];
			  String action_parametre = LecteurJSON.Get_Action()[2];
			  switch (action_page) {
			  case "PROFIL" :
				  switch(action_bouton) {
				  case "SHOW_ONE_PROFIL" :
					  System.out.println("SHOW ONE PROFIL");
					  profil.Profil.Action_Show_One(action_parametre);
					  break;

				  case "SHOW_ALL_PROFIL":
					  System.out.println("SHOW ALL PROFIL");
					  profil.Profil.Action_Show_All();
					  break;

				  case "UPDATE_ONE_PROFIL":	
					  System.out.println("UPDATE ONE PROFIL");
					  profil.Profil.Action_Update_One(action_parametre);
					  break;
					  
				  case "UPDATE_ALL_PROFIL":
					  System.out.println("UPDATE ALL PROFIL");
					  profil.Profil.Action_Update_All();
					  break;
				  case "RETOUR" :
						System.out.println("FERME");

					try {
						Serveur.sv.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  	System.exit(0);
					  	break;
				  }
			 
			  case "GESTION EMPLACEMENT" :
				  switch(action_bouton) {
				  case"":
					  
				  }
			  }
			  try {
				Serveur.Envoyer_JSON();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	
	}

	
}
