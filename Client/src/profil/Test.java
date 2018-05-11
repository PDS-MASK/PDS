package profil;

import java.io.IOException;

public class Test {

	public static void generateur_test(String id, String sous_categorie, String nbr) {
		JSON.GenerateurJSON.Ecriture_JSON_TEST("Test","TEST_PROFIL",id,sous_categorie,nbr);
		try {
			JSON.Client.Envoyer_Json();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}
}
