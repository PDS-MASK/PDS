package indicateur;


import java.lang.reflect.Array;
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
			ArrayList<String> boutiques = new ArrayList<>();
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
public float achatsjour() throws SQLException {
	
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
}
