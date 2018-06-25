package parcourstype_view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Sous_Article {
	
	private int id_sousarticle;
	private int id_boutique;
	private int id_article;
	private String taille;
	private String couleur;
	private float prix;
	private int stock;
	
	
	void ShowItineraryByPreference(int i , String preference)

	{

		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");	
			Statement stmt = null;
			stmt = connection.createStatement();
			String sql =

					"select count(commande.id_commande)as nbvente,sous_article.id_boutique,boutique.nom_boutique " + 
							"from commande_sousarticle, sous_article, article, sous_categorie, souscategorie_article , boutique , commande " + 
							"where sous_article.ID_BOUTIQUE = boutique.ID_BOUTIQUE " + 
							"and commande_sousarticle.id_sousarticle = sous_article.id_sousarticle " + 
							"and sous_article.ID_ARTICLE = article.id_article "+ 
							"and souscategorie_article.id_article=article.id_article " + 
							"and souscategorie_article.id_souscategorie = sous_categorie.id_souscategorie " + 
							"and commande.ID_commande = commande_sousarticle.ID_COMMANDE " + 
							"and sous_categorie.nom_souscategorie = '"+preference+"'"+
							"and commande.id_personne = '"+i+"'"+
							"group by sous_article.id_boutique, boutique.nom_boutique "+ 
							"order by count(commande.id_commande)desc";


			Object pst = connection.prepareStatement(sql);
			ResultSet rs = ((PreparedStatement) pst).executeQuery();
			String resultat = " ";
			System.out.print(" "+ rs);
			
			
			 while(((ResultSet) rs).next()){
				  resultat = resultat +i+" - "+((ResultSet) rs).getString("nom_boutique")+"\n";
				  i++;
				 }
			 
			 System.out.println(resultat);
				JOptionPane.showMessageDialog(null,resultat,"Voici le parcours ! ", JOptionPane.INFORMATION_MESSAGE);
				
			 
			}
		
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			 
			}


	}
	
	public int getId_sousarticle() {
		return id_sousarticle;
	}
	public void setId_sousarticle(int id_sousarticle) {
		this.id_sousarticle = id_sousarticle;
	}
	public int getId_boutique() {
		return id_boutique;
	}
	public void setId_boutique(int id_boutique) {
		this.id_boutique = id_boutique;
	}
	public int getId_article() {
		return id_article;
	}
	public void setId_article(int id_article) {
		this.id_article = id_article;
	}
	public String getTaille() {
		return taille;
	}
	public void setTaille(String taille) {
		this.taille = taille;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
