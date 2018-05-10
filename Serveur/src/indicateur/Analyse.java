package indicateur;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Analyse {
	public Analyse () {
		
	}
	public static void BoutiquesDispo() throws SQLException {
		
		Connection connexion = Bdd.getConnection();
		System.out.println("sql");
		List<Emplacement> emplacement = new ArrayList<Emplacement>();
		ArrayList<String> l_nom = new ArrayList<String>();
		ArrayList<String> l_surface = new  ArrayList<String>();
		Statement ordre = connexion.createStatement();
		String sql = "SELECT NOM_EMPLACELEMENT, SUPERFICIE from emplacement e, historique_emplacement h where e.id_emplacement = h.id_emplacement and date_ is null";
		ResultSet rs = ordre.executeQuery(sql);
		while(rs.next()) {
			System.out.println("if");
			l_nom.add(rs.getString(1));
			l_surface.add(rs.getString(2));
			
			System.out.println("boutiques");
			
		}
				
		ordre.close();
		connexion.close();
		GenerateurJSON.Ecriture_JSON(l_nom,l_surface);
		System.out.println("ecriture json");
		try {
			System.out.println("ENVOIE JSON");
			administration.Serveur.Envoyer_JSON();
			System.out.println("ENVOIE JSON");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
public static void afficheP(String p) throws SQLException {
		
		Connection connexion = Bdd.getConnection();
		int cpt = 0;
		Statement ordre = connexion.createStatement();
		String sql = "SELECT date_visite - sysdate as datediff from visite  ";
		System.out.println("req");
		ResultSet rs = ordre.executeQuery(sql);
		
		while(rs.next()) {
			if (rs.getRow()<= 1) {
				cpt++;
				System.out.println("p");

			}
			
		}
		
		
		ordre.close();
		connexion.close();
		GenerateurJSON.Ecriture_JSON_Achats(Integer.toString(cpt));
		System.out.println("ecriture json");
		try {
			System.out.println("ENVOIE JSON");
			administration.Serveur.Envoyer_JSON();
			System.out.println("ENVOIE JSON");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;

	}
public static void affichePsemestre(String p) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	int cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "SELECT date_visite - sysdate as datediff from visite  ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	while(rs.next()) {
		if (rs.getRow()<= 90) {
			cpt++;
			System.out.println("p");

		}
		
	}
	
	
	ordre.close();
	connexion.close();
	GenerateurJSON.Ecriture_JSON_Achats(Integer.toString(cpt));
	System.out.println("ecriture json");
	try {
		System.out.println("ENVOIE JSON");
		administration.Serveur.Envoyer_JSON();
		System.out.println("ENVOIE JSON");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ;

}
public static void affichePannee(String p) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	int cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "SELECT date_visite - sysdate as datediff from visite  ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	while(rs.next()) {
		if (rs.getRow()<= 365) {
			cpt++;
			System.out.println("p");

		}
		
	}
	
	
	ordre.close();
	connexion.close();
	GenerateurJSON.Ecriture_JSON_Achats(Integer.toString(cpt));
	System.out.println("ecriture json");
	try {
		System.out.println("ENVOIE JSON");
		administration.Serveur.Envoyer_JSON();
		System.out.println("ENVOIE JSON");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ;

}
public static void achatsjour(String id_boutique) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	float cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "select sum(prix_commande) from commanda_sousarticle c, sous_article sc , commande co where co.ID_COMMANDE = c.ID_COMMANDE and c.ID_SOUSARTICLE = sc.ID_SOUSARTICLE and sc.ID_BOUTIQUE ="+ id_boutique +"and TO_DATE(sysdate, 'yyyy/mm/dd') = TO_DATE(co.DATE_COMMANDE, 'yyyy/mm/dd') ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	if(rs.next()) {
			cpt = rs.getFloat(1);
			System.out.println("p");

		
	}
	GenerateurJSON.Ecriture_JSON_Achats(Float.toString(cpt));
	System.out.println("ecriture json");
	try {
		System.out.println("ENVOIE JSON");
		administration.Serveur.Envoyer_JSON();
		System.out.println("ENVOIE JSON");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ordre.close();
	connexion.close();
	return ;

}
public static void achatsmois(String id_boutique) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	float cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "select sum(prix_commande) from commanda_sousarticle c, sous_article sc , commande co where co.ID_COMMANDE = c.ID_COMMANDE and c.ID_SOUSARTICLE = sc.ID_SOUSARTICLE and sc.ID_BOUTIQUE ="+ id_boutique +"and to_char(sysdate, 'MM') = to_char(co.DATE_COMMANDE, 'MM') ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	if(rs.next()) {
		
			cpt = rs.getFloat(1);
			System.out.println("p");

		
		
	}
	
	
	ordre.close();
	connexion.close();
	GenerateurJSON.Ecriture_JSON_Achats(Float.toString(cpt));
	System.out.println("ecriture json");
	try {
		System.out.println("ENVOIE JSON");
		administration.Serveur.Envoyer_JSON();
		System.out.println("ENVOIE JSON");

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ;

}
}
