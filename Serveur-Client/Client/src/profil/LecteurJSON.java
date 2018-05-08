package profil;


import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LecteurJSON {

	
	public static Object[][] Get_Liste() {
	      // Ouverture du fichier
        FileInputStream fs = null;
        try {
            fs = new FileInputStream("json_serveur");
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
      
        JSONArray tmp_ID = objet.getJSONArray("Liste_ID");
        JSONArray tmp_NOM = objet.getJSONArray("Liste_NOM");
        JSONArray tmp_PRENOM = objet.getJSONArray("Liste_PRENOM");
        JSONArray tmp_PROFIL = objet.getJSONArray("Liste_PROFIL");
        JSONArray tmp_CATEGORIE = objet.getJSONArray("Liste_CATEGORIE");
        Object [][] Liste = new Object[tmp_ID.length()+1][5];

        for (int i = 0;i<tmp_ID.length();i++) {
        	Liste[i][0] = tmp_ID.get(i);
        	Liste[i][1] = tmp_NOM.get(i);
        	Liste[i][2] = tmp_PRENOM.get(i);
        	Liste[i][3] = tmp_PROFIL.get(i);
        	Liste[i][4] = tmp_CATEGORIE.get(i);

        	
        }



      
        
     
        return Liste;
    }
}