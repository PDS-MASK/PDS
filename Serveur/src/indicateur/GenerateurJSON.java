package indicateur;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GenerateurJSON {

    public static void Ecriture_JSON(ArrayList<String> nom, ArrayList<String> surface) {
        // V�rification des arguments
        
        // G�n�ration du JSON depuis un tableau d'objets
       System.out.println("ECRITURE JSON SERVEUR");
        JSONObject objet = new JSONObject();
        
        // Ajout du tableau
        try {
        	  	
            objet.put("Liste_NOM", new JSONArray(nom));
            objet.put("Liste_SURFACE", new JSONArray(surface));
            




        } catch(JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(-1);
        }
        
        // Cr�ation du fichier de sortie
        FileWriter fs = null;
        try {
            fs = new FileWriter("json_serveur.json");
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier 'json_serveur.json'.");
            System.err.println(e);
            System.exit(-1);
        }
	
        // Sauvegarde dans le fichier
        try {
            objet.write(fs, 3, 0);
            fs.flush();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'�criture dans le fichier.");
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
        
        System.out.println("Le fichier 'json_serveur.json' a �t� g�n�r�.");
    }
    public static void Ecriture_JSON_Achats(String achats) {
        // V�rification des arguments
        
        // G�n�ration du JSON depuis un tableau d'objets
       System.out.println("ECRITURE JSON SERVEUR");
        JSONObject objet = new JSONObject();
        
        // Ajout du tableau
        try {
        	  	
            objet.put("ACHATS", achats);
            




        } catch(JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(-1);
        }
        
        // Cr�ation du fichier de sortie
        FileWriter fs = null;
        try {
            fs = new FileWriter("json_serveur.json");
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier 'json_serveur.json'.");
            System.err.println(e);
            System.exit(-1);
        }
	
        // Sauvegarde dans le fichier
        try {
            objet.write(fs, 3, 0);
            fs.flush();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'�criture dans le fichier.");
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
        
        System.out.println("Le fichier 'json_serveur.json' a �t� g�n�r�.");
    }
}