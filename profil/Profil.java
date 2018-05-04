package profil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Profil {

	private int id_person;
	private ArrayList<String> profil;
	private ArrayList<String> catégorie;
	
	public Profil(int id) throws SQLException {
		this.id_person = id;
		this.profil = new ArrayList<String>();
		this.catégorie = new ArrayList<String>();
		this.Define_profil();
		this.Define_categorie();
	}
	public Profil() throws SQLException{
		 try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		      Profil g = null;
		      Statement stmt = conn.createStatement();
		      ResultSet rset = stmt.executeQuery("select id_personne from personne");
				while (rset.next()) {
					g = new Profil(rset.getInt(1));
				}
			} catch (Exception e) {
		      e.printStackTrace();
		    }      
		
	}
	
	public int get_id() {
		System.out.println(this.id_person);
		return this.id_person;
	}
	
	public void Insert_Data_Profil() throws SQLException {
		ArrayList<String> tmp_profil = new ArrayList<String>();
		for (int i = 0;i<this.profil.size();i++) {
			tmp_profil.add("false");
		}
		int index = 0 ;
		 try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		      Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select profil.nom_profil,profil.id_profil from profil,profil_personne where profil.ID_PROFIL = profil_personne.ID_PROFIL and profil_personne.ID_PERSONNE = " + this.id_person);
				while (rset.next()) {
					if(!(this.profil.contains(rset.getString(1)))) {
						stmt.executeQuery("delete from profil_personne where id_personne=" + this.id_person +" and id_profil ='"+rset.getString(2)+"'");
					}
					else {
						tmp_profil.set(index,"true");
					}
					index++;
					
				}
				for(int i=0; i<tmp_profil.size();i++) {
					if (tmp_profil.get(i).equals("false")) {
						stmt.executeQuery("insert into profil_personne values("+this.id_person+",(Select id_profil from profil where nom_profil='"+this.profil.get(i)+"'))");
					}
				}
		    } catch (Exception e) {
		      e.printStackTrace();
		    }      

		
		
	}
	
	public void Insert_Data_Categorie() throws SQLException {
		ArrayList<String> tmp_categorie = new ArrayList<String>();
		for (int i = 0;i<this.catégorie.size();i++) {
			tmp_categorie.add("false");
		}
		int index = 0 ;
		 try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");   
		  	Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("select id_souscategorie from categorie_personne where id_personne =" + this.id_person);
			while (rset.next()) {
				if(!(this.catégorie.contains(rset.getString(1)))) {
					stmt.executeQuery("delete from categorie_personne where id_personne=" + this.id_person +" and id_souscategorie ="+rset.getString(1)+"");
				}
				else {
					tmp_categorie.set(index,"true");
				}
				index++;
				
			}
			for(int i=0; i<tmp_categorie.size();i++) {
				if (tmp_categorie.get(i).equals("false")) {
					stmt.executeQuery("insert into categorie_personne values("+this.id_person+",'"+this.catégorie.get(i));
				}
			}
			
		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }      
	
		
	}
	
	
	
	
	public void Define_profil() throws SQLException {
		//Connection à faire
		// Requete sur les achats en particulier d'un profil
		try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");    
		      Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select date_commande from commande where id_personne ="+ this.id_person);
				int Nbr_Semaine = 0;
				int Nbr_Mois = 0;
				int Nbr_Year = 0;
				Calendar week = Calendar.getInstance();
				Calendar month = Calendar.getInstance();
				Calendar year = Calendar.getInstance();
				week.add(Calendar.DAY_OF_MONTH,-7);
				month.add(Calendar.MONTH,-1);
				year.add(Calendar.YEAR, -1);
				Date dat_week = week.getTime();
				Date dat_month = month.getTime();
				Date dat_year = year.getTime();
				System.out.println("ANNEE :" + dat_year);
				System.out.println("ANNEE :" + dat_month);
				System.out.println("ANNEE :" + dat_week);
				while (rset.next()) {
					
					if (rset.getDate(1).after(dat_week)) {
						System.out.println("Passe à une semaine");
						Nbr_Semaine++;
						Nbr_Mois++;		
						Nbr_Year++;
					}
					if (rset.getDate(1).after(dat_month)) {
						System.out.println("Passe au mois");

						Nbr_Mois++;
						Nbr_Year++;
					}
					if(rset.getDate(1).before(dat_month) && rset.getDate(1).after(dat_year)) {
						Nbr_Year++;
						System.out.println("Passe à l'année");

					}
					
				}
				if(Nbr_Semaine > 6) {
					(this.profil).add("Compulsif");
				}
				else if(Nbr_Mois > 4) {
					(this.profil).add("Normal");
				}
				else if(Nbr_Mois >= 1) {
					(this.profil).add("Modere");
				}
				else if(Nbr_Year > 1) {
					(this.profil).add("Absent");
				}
		         
		    } catch (Exception e) {
		      e.printStackTrace();
		    }      
		for(int i=0;i<this.profil.size();i++) {
			System.out.println(this.profil.get(i));
		}
		 this.Profil_Econome();
		 this.Insert_Data_Profil();

	}
	
	public void Define_categorie() throws SQLException {
		 try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";

		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		         
		      Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select count(*),sous_categorie.id_souscategorie from sous_categorie, souscategorie_article, sous_article, commande_sousarticle, commande where date_commande > sysdate - interval '3' MONTH and commande.ID_COMMANDE = commande_sousarticle.ID_COMMANDE and commande_sousarticle.ID_SOUSARTICLE = sous_article.ID_SOUSARTICLE and sous_article.ID_ARTICLE = souscategorie_article.ID_ARTICLE and souscategorie_article.ID_SOUSCATEGORIE = sous_categorie.ID_SOUSCATEGORIE and commande.ID_PERSONNE = "+this.id_person +" group by sous_categorie.id_souscategorie");
				while (rset.next()) {
					if(rset.getInt(1) >= 3) {
						(this.catégorie).add(rset.getString(2));

					}
				}
			} catch (Exception e) {
		      e.printStackTrace();
		    }      
		
		 this.Insert_Data_Categorie();
	}
	
	public void Profil_Econome() throws SQLException {
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 System.out.println("Driver O.K.");
		      String url = "jdbc:oracle:thin:@localhost:1521/xe";
		      String user = "moi";
		      String passwd = "moi";
		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");         
		         
		      Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select count(*) from commande,commande_sousarticle,reduction where date_commande > sysdate - interval '3' MONTH and commande.id_commande = commande_sousarticle.id_commande and COMMANDE_SOUSARTICLE.ID_SOUSARTICLE = reduction.ID_SOUSARTICLE and commande.DATE_COMMANDE between reduction.DATE_DEBUT_REDUC and reduction.DATE_FIN_REDUC and id_personne = "+this.id_person);
				while (rset.next()) {
					if(rset.getInt(1) >= 3) {
						(this.catégorie).add("Econome");
					}
				}
			} catch (Exception e) {
			      e.printStackTrace();
			}
	}
			
	
}
