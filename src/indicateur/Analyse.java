package indicateur;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Analyse {
	public Analyse () {
		
	}
	public List<Emplacement> BoutiquesDispo() throws SQLException {
		
		Connection connexion = Bdd.getConnection();
		List<Emplacement> emplacement = new ArrayList<Emplacement>();
		Statement ordre = connexion.createStatement();
		String sql = "SELECT NOM_EMPLACELEMENT, SUPERFICIE from emplacement e, historique_emplacement h where e.id_emplacement = h.id_emplacement and date_ is null";
		ResultSet rs = ordre.executeQuery(sql);
		while(rs.next()) {
			System.out.println("if");
			emplacement.add(new Emplacement(rs.getString(1), rs.getString(2)));
			
			System.out.println("boutiques");
			
		}
				
		ordre.close();
		connexion.close();
		return emplacement;
	}
public int afficheP() throws SQLException {
		
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
		return cpt;

	}
public int affichePsemestre() throws SQLException {
	
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
	return cpt;

}
public int affichePannee() throws SQLException {
	
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
	return cpt;

}
public float achatsjour(String id_boutique) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	float cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "select sum(prix_commande) from commanda_sousarticle c, sous_article sc , commande co where co.ID_COMMANDE = c.ID_COMMANDE and c.ID_SOUSARTICLE = sc.ID_SOUSARTICLE and sc.ID_BOUTIQUE ="+ id_boutique +"and TO_DATE(sysdate, 'yyyy/mm/dd') = TO_DATE(co.DATE_COMMANDE, 'yyyy/mm/dd') ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	while(rs.next()) {
			cpt = rs.getFloat(1);
			System.out.println("p");

		
	}
	
	
	ordre.close();
	connexion.close();
	return cpt;

}
public float achatsmois(String id_boutique) throws SQLException {
	
	Connection connexion = Bdd.getConnection();
	float cpt = 0;
	Statement ordre = connexion.createStatement();
	String sql = "select sum(prix_commande) from commanda_sousarticle c, sous_article sc , commande co where co.ID_COMMANDE = c.ID_COMMANDE and c.ID_SOUSARTICLE = sc.ID_SOUSARTICLE and sc.ID_BOUTIQUE ="+ id_boutique +"and to_char(sysdate, 'MM') = to_char(co.DATE_COMMANDE, 'MM') ";
	System.out.println("req");
	ResultSet rs = ordre.executeQuery(sql);
	
	while(rs.next()) {
		
			cpt = rs.getFloat(1);
			System.out.println("p");

		
		
	}
	
	
	ordre.close();
	connexion.close();
	return cpt;

}
}
