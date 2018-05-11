package JSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.FileWriter;
import java.io.IOException;


public class GenerateurJSON {
	
	public static void Ecriture_JSON(String action_bouton,String page,String parametre) {
		String action = action_bouton;
		 JSONObject objet = new JSONObject();
	        
	        // Ajout du tableau
	        try {
	            objet.put("Action_Bouton", action_bouton);
	            objet.put("Action_Page", page);
	            objet.put("Action_parametre", parametre);

	        } catch(JSONException e) {
	            System.err.println("Erreur lors de l'insertion du tableau.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        // Création du fichier de sortie
	        FileWriter fs = null;
	        try {
	            fs = new FileWriter("json.json");
	        } catch(IOException e) {
	            System.err.println("Erreur lors de l'ouverture du fichier 'json'.");
	            System.err.println(e);
	            System.exit(-1);
	        }
		
	        // Sauvegarde dans le fichier
	        try {
	            objet.write(fs, 3, 0);
	            fs.flush();
	        } catch(IOException e) {
	            System.err.println("Erreur lors de l'écriture dans le fichier.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        // Fermeture du fichier
	        try {
	            fs.close();
	        } catch(IOException e) {
	            System.err.println("Erreur lors de la fermeture du fichier.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        System.out.println("Le fichier 'json' a été généré.");
	    }
	public static void Ecriture_JSON_TEST(String action_bouton,String page,String id,String  nbr, String sous_categorie) {
		String action = action_bouton;
		 JSONObject objet = new JSONObject();
	        
	        // Ajout du tableau
	        try {
	            objet.put("Action_Bouton", action_bouton);
	            objet.put("Action_Page", page);
	            objet.put("Action_id", id);
	            objet.put("Action_nbr", nbr);
	            objet.put("Action_sous_categorie", sous_categorie);


	        } catch(JSONException e) {
	            System.err.println("Erreur lors de l'insertion du tableau.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        // Création du fichier de sortie
	        FileWriter fs = null;
	        try {
	            fs = new FileWriter("json.json");
	        } catch(IOException e) {
	            System.err.println("Erreur lors de l'ouverture du fichier 'json'.");
	            System.err.println(e);
	            System.exit(-1);
	        }
		
	        // Sauvegarde dans le fichier
	        try {
	            objet.write(fs, 3, 0);
	            fs.flush();
	        } catch(IOException e) {
	            System.err.println("Erreur lors de l'écriture dans le fichier.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        // Fermeture du fichier
	        try {
	            fs.close();
	        } catch(IOException e) {
	            System.err.println("Erreur lors de la fermeture du fichier.");
	            System.err.println(e);
	            System.exit(-1);
	        }
	        
	        System.out.println("Le fichier 'json' a été généré.");
	    }

    
}