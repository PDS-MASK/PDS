package parcourstype_view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Sous_Categorie {
	
	private int id_souscategorie;
	private int id_categorie;
	private String nom_souscategorie;
	public static ArrayList<String> preference = new ArrayList<String>();

	
	
	
	public int getId_souscategorie() {
		return id_souscategorie;
	}
	public void setId_souscategorie(int id_souscategorie) {
		this.id_souscategorie = id_souscategorie;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getNom_souscategorie() {
		return nom_souscategorie;
	}
	public void setNom_souscategorie(String nom_souscategorie) {
		this.nom_souscategorie = nom_souscategorie;
	}
	
	public static String showSelectedConsPurchasePreference(int i, JTextField SelectedProfilPurchasePreference)
	{
			String resultat = " ";
			try{
			Connection connection = Personne.getPool().getConnection();
			//Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
			String sql = "select nom_souscategorie from sous_categorie , categorie_personne where sous_categorie.id_souscategorie = categorie_personne.ID_SOUSCATEGORIE and categorie_personne.ID_PERSONNE ="+i;
			Object pst = connection.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			
			System.out.print(" "+ rs);
			 while(((ResultSet) rs).next()){
				  resultat = resultat+((ResultSet) rs).getString(i);
				  preference.add(((ResultSet) rs).getString(i));
				 }
			 SelectedProfilPurchasePreference.setText(resultat);
			}
			catch(Exception ex){
			//JOptionPane.showMessageDialog(null, ex);
			 
			}
			
			return resultat;
			 
			
		
		
	}
	public static ArrayList<String> getPreference() {
		return preference;
	}
	public static void setPreference(ArrayList<String> preference) {
		Sous_Categorie.preference = preference;
	}
	
	
	

}
