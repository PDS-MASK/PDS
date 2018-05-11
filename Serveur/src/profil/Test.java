package profil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
	
	public static void generateur_test(int id,String sous_categorie, int nbr) {
		 try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		      Statement stmt = conn.createStatement();
	    	  for(int i=0;i<nbr;i++) {
			      stmt.executeQuery(" insert into commande values(seq_commande.nextval,"+id+",sysdate)");
			      ResultSet rset = stmt.executeQuery("SELECT id_sousarticle FROM( SELECT id_sousarticle FROM sous_article,article,sous_categorie,souscategorie_article where sous_article.id_article = article.id_article and article.id_article = souscategorie_article.id_article and souscategorie_article.id_souscategorie = sous_categorie.id_souscategorie and nom_souscategorie = '"+sous_categorie+"' ORDER BY dbms_random.value) WHERE rownum = 1" );
			      	if(rset.next()) {
			    	  int id_sousarticle = rset.getInt(1);
					  stmt.executeQuery("insert into commande_sousarticle values (seq_commande.currval,"+id_sousarticle+",(select prix from sous_article where id_sousarticle ="+id_sousarticle+"),1)");
			      	}   
		      }
	    	  
		      
		      
	 } catch (Exception e) {
	      e.printStackTrace();
	    }      
		 Profil.Action_Show_One(Integer.toString(id));
		 try {
			administration.Serveur.Envoyer_JSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
