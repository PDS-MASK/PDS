import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
amine

public class GenerateurJSON {

    public static void main(String[] args) {
        // Vérification des arguments
        if(args.length != 1) {
            System.err.println("Erreur : vous devez spécifier le nom du fichier JSON.");
            System.err.println();
            System.err.println("Usage : java GenerateurJSON fichier.json");
            System.err.println("\toù 'fichier.json' est le nom du fichier dans lequel sauvegarder");
            System.exit(-1);
        }

        // Génération du JSON depuis un tableau d'objets
        Personne p[] = { new Personne("Sami", "Safsaf", 21),
                         new Personne("Mohamed", "Ben Malek", 22),
                         new Personne("Amine", "Hammidi", 20),
                         new Personne("Marie", "Rousselet", 22),
                         new Personne("Khadija", "Boudabous", 22)
                        };
        JSONObject objet = new JSONObject();
        
        // Ajout du tableau
        try {
            objet.put("contacts", new JSONArray(p));
        } catch(JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(-1);
        }
        
        // Création du fichier de sortie
        FileWriter fs = null;
        try {
            fs = new FileWriter(args[0]);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + args[0] + "'.");
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
        
        System.out.println("Le fichier '" + args[0] + "' a été généré.");
    }
}