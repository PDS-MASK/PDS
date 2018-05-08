package administration;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;

import profil.Profil;

public class Serveur {
	  static Socket sock;
	    static ServerSocket sv;
	    
	   public static void Envoyer_JSON() throws IOException {
		   if(sock.isClosed()) {
			   sock =sv.accept();

		   }
           BufferedOutputStream outToClient = new BufferedOutputStream(sock.getOutputStream());
           if (outToClient != null) {
               File myFile = new File("json_serveur.json");
               byte[] mybytearray = new byte[(int) myFile.length()];

               FileInputStream fis = null;

               try {
                   fis = new FileInputStream(myFile);
               } catch (FileNotFoundException ex) {
                   // Do exception handling
               }
               BufferedInputStream bis = new BufferedInputStream(fis);

               try {
                   bis.read(mybytearray, 0, mybytearray.length);
                   outToClient.write(mybytearray, 0, mybytearray.length);
                   outToClient.flush();
                   outToClient.close();
                   sock.close();
                   // File sent, exit the main method
                   return;
               } catch (IOException ex) {
                   // Do exception handling
               }
           }

	   }
	    
		  public static void main (String [] args ) throws IOException, InterruptedException {
			  if(sv == null ) {
				  sv = new ServerSocket(9003);
			  } else if ( sv.isClosed()) {
				  sv = new ServerSocket(9003);
			  }
			  if(sock == null) {
				  sock = sv.accept();
			  }
			  else if(sock.isClosed()) {
				  sock = sv.accept();
			  }
			  JSON.Commun.transfert( sock.getInputStream(), new FileOutputStream("json.json"), true);
			  sock.close();
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
	
					  	sv.close();
					  	System.exit(0);
					  	break;
				  }
			 
			  case "GESTION EMPLACEMENT" :
				  switch(action_bouton) {
				  case"":
					  
				  }
			  }
			  Envoyer_JSON();
	          Thread.sleep(4000);
			//  sv.close();
		  }
}