package parcourstype_view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JTextField;

public class Emplacement {
	private int id_emplacement;
	private String nom_emplacement;
	private	String disponibilite;
	private int superficie;
	private int redevance;
	public static HashMap<String, String> hmap = new HashMap<String, String>();
	
	
	public int getId_emplacement() {
		return id_emplacement;
	}
	public void setId_emplacement(int id_emplacement) {
		this.id_emplacement = id_emplacement;
	}
	public String getNom_emplacement() {
		return nom_emplacement;
	}
	public void setNom_emplacement(String nom_emplacement) {
		this.nom_emplacement = nom_emplacement;
	}
	public String getDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public int getRedevance() {
		return redevance;
	}
	public void setRedevance(int redevance) {
		this.redevance = redevance;
	}
	
	public static String showAllShop()
	{
			String resultat = "ITS OK";
			try{
			Connection connection = Personne.getPool().getConnection();
			//Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
			String sql = "select boutique.nom_boutique , emplacement.nom_emplacement , theme.nom_theme from boutique,emplacement,emplacement_boutique,THEME,THEME_BOUTIQUE where boutique.ID_BOUTIQUE = emplacement_boutique.ID_BOUTIQUE and emplacement.ID_EMPLACEMENT = emplacement_boutique.ID_EMPLACEMENT and boutique.ID_BOUTIQUE = THEME_BOUTIQUE.ID_BOUTIQUE and THEME.ID_THEME = THEME_BOUTIQUE.ID_THEME order by emplacement.ID_EMPLACEMENT";
			Object pst = connection.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			
			System.out.print(" "+ rs);
			int cpt =0;
			 while(((ResultSet) rs).next()){
				 String nom_boutique = ((ResultSet) rs).getString("nom_boutique");
				 String nom_theme = ((ResultSet) rs).getString("nom_theme");
				 hmap.put(nom_boutique,nom_theme);
				 cpt++;
				 
				 }
			 
			 for (HashMap.Entry<String, String> entry : hmap.entrySet()) {
				   System.out.println("[" + entry.getKey() +  "] -> " + entry.getValue()) ;
				}
			 System.out.println(hmap.size());
			 System.out.println("cpt : "+cpt);
			}
			catch(Exception ex){
			//JOptionPane.showMessageDialog(null, ex);
			 
			}
			
			return resultat;
			 
			
		
		
	}
	

}
