package indicateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Analyse {
	public Analyse () {
		
	}
	public ArrayList<String> BoutiquesDispo() throws SQLException {
		
		Connection connexion = Bdd.getConnection();
		//int cpt = 0;
		ArrayList<String> boutiques = new ArrayList<String>();
		
		
		Statement ordre = connexion.createStatement();
		String sql = "SELECT nom_boutique from boutique b, historique_emplacement h where b.id_boutique = h.id_boutique and date_ is null";

		ResultSet rs = ordre.executeQuery(sql);
		
		if(rs.next()) {
			System.out.println("if");
			boutiques.add(rs.getString(1));
			System.out.println("boutiques");
			
		}
		
		
		ordre.close();
		connexion.close();
		return boutiques;

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
}
