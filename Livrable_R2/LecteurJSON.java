import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LecteurJSON {

    public static void main(String[] args) {
        // Vérification des arguments
        if(args.length != 1) {
            System.err.println("Erreur : vous devez spécifier le nom du fichier JSON.");
            System.err.println();            
            System.err.println("Usage : java LecteurJSON fichier.json");
            System.err.println("\toù 'fichier.json' est le nom du fichier à ouvrir");
            System.exit(-1);
        }

        // Ouverture du fichier
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(args[0]);
        } catch(FileNotFoundException e) {
            System.err.println("Fichier '" + args[0] + "' introuvable");
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
        System.out.println(json);

        // Affichage à l'écran
        JSONArray tableau = objet.getJSONArray("contacts");
        System.out.println("Liste des personnes :");
        for(int i = 0; i < tableau.length(); i++) {
            JSONObject element = tableau.getJSONObject(i);
            System.out.print("nom=" + element.getString("nom"));
            System.out.print(", prenom=" + element.getString("prenom"));
            System.out.println(", age=" + element.getInt("age"));
        }
    }
}