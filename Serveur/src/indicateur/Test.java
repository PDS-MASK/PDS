package indicateur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	public static void testvisitejour(String m) throws SQLException {
		Connection connexion = Bdd.getConnection();
		float cpt = 0;
		Statement ordre = connexion.createStatement();
		String sql = "INSERT INTO \"MOI\".\"COMMANDE\" (ID_COMMANDE, ID_PERSONNE, DATE_COMMANDE) VALUES ('5', '3', sysdate))";
		String sql1 = "\"INSERT INTO \\\"MOI\\\".\\\"COMMANDE\\\" (ID_COMMANDE, ID_PERSONNE, DATE_COMMANDE) VALUES ('5', '3', sysdate))\";\r\n";
		indicateur.Analyse.afficheP("");

		System.out.println("req");
		ResultSet rs = ordre.executeQuery(sql);
		ResultSet rs1= ordre.executeQuery(sql1);
		indicateur.Analyse.afficheP("");
	
//		GenerateurJSON.Ecriture_JSON_Achats(Float.toString(cpt));
//		System.out.println("ecriture json");
//		try {
//			System.out.println("ENVOIE JSON");
//			administration.Serveur.Envoyer_JSON();
//			System.out.println("ENVOIE JSON");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ordre.close();
		connexion.close();
		return ;
		
	}
}
