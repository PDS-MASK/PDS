package administration;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LecteurJSON {
	
	public static String[] Get_Action() {
	      // Ouverture du fichier
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("json.json");
        } catch(FileNotFoundException e) {
            System.err.println("Fichier 'json' introuvable");
            System.exit(-1);
        }

        // Récupération de la chaîne JSON depuis le fichier
        String json = new String();
        Scanner scanner = new Scanner(fs);
        while(scanner.hasNext())
            json += scanner.nextLine();
        scanner.close();
        json = json.replaceAll("[\t ]", "");

        // Fermeture du fichier
        try {
            fs.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier.");
            System.err.println(e);
            System.exit(-1);
        }        
        
        // Création d'un objet JSON
        JSONObject objet = new JSONObject(json);
        System.out.println("Contenu JSON : ");
        System.out.println(objet.getString("Action_Bouton"));
        
        String action_page = objet.getString("Action_Page");
        String action_bouton = objet.getString("Action_Bouton");
        String action_parametre = objet.getString("Action_parametre");

        switch(action_page) {
        case "" : 
        	
        }
        // Affichage à l'écran
       String[] retour = new String[3];
       retour[0] = action_bouton;
       retour[1] = action_page;
       retour[2] = action_parametre;
        return retour;
    }


    
}