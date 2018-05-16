package parcourstype_view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class Personne {
	
	private int id_personne;
	private String nom_personne;
	private String prenom_personne;
	private String sexe;
	private Date date_de_naissance;
	private Date date_ajout;
	private int telephone;
	private String adresse_personne;
	private String adresse_mail_personne;

	
	
	int getId_personne() {
		return id_personne;
	}
	public void setId_personne(int id_personne) {
		this.id_personne = id_personne;
	}
	public String getNom_personne() {
		return nom_personne;
	}
	public void setNom_personne(String nom_personne) {
		this.nom_personne = nom_personne;
	}
	public String getPrenom_personne() {
		return prenom_personne;
	}
	public void setPrenom_personne(String prenom_personne) {
		this.prenom_personne = prenom_personne;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Date getDate_de_naissance() {
		return date_de_naissance;
	}
	public void setDate_de_naissance(Date date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}
	public Date getDate_ajout() {
		return date_ajout;
	}
	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getAdresse_personne() {
		return adresse_personne;
	}
	public void setAdresse_personne(String adresse_personne) {
		this.adresse_personne = adresse_personne;
	}
	public String getAdresse_mail_personne() {
		return adresse_mail_personne;
	}
	public void setAdresse_mail_personne(String adresse_mail_personne) {
		this.adresse_mail_personne = adresse_mail_personne;
	}
	
	
	public void showTableData(JTable table){
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
		String sql = "Select ID_PERSONNE,NOM_PERSONNE,PRENOM_PERSONNE from PERSONNE order by ID_PERSONNE";
		Object pst = connection.prepareStatement(sql);
		Object rs = ((PreparedStatement) pst).executeQuery();
		table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
		
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		 
		}
	

	public static String showSelectedIdCons(int i, JTextField Name_Selected_Cons){
		
		System.out.println(i);
		String resultat = " ";
		String nom = null;
		String prenom = null;
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
		String sql = "Select NOM_PERSONNE,PRENOM_PERSONNE from PERSONNE where id_personne ="+i;
		Object pst = connection.prepareStatement(sql);
		ResultSet rs = ((PreparedStatement) pst).executeQuery();
		
		
		 while(((ResultSet) rs).next()){
			  //resultat = ((ResultSet) rs).getString("nom_personne")+((ResultSet) rs).getString("prenom_personne");
			  nom = ((ResultSet) rs).getString("nom_personne");
			  prenom = ((ResultSet) rs).getString("prenom_personne");
			  
			 }
		 resultat = nom+prenom;
		// Name_Selected_Cons.setText(resultat);
		 
		 
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		System.out.println(resultat);
		return resultat;
		 
		}
	
	public void showSelectedConsProfil(int i, JTextField SelectedProfilCons){
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
		String sql = "Select nom_profil from PROFIL,PROFIL_PERSONNE,PERSONNE WHERE profil_personne.id_profil = PROFIL.ID_PROFIL and PROFIL_PERSONNE.ID_PERSONNE = PERSONNE.ID_PERSONNE and PERSONNE.ID_PERSONNE ="+i;
		Object pst = connection.prepareStatement(sql);
		Object rs = ((PreparedStatement) pst).executeQuery();
		String resultat = " ";
		System.out.print(" "+ rs);
		 while(((ResultSet) rs).next()){
			  resultat = resultat+((ResultSet) rs).getString(i);
			 }
		 SelectedProfilCons.setText(resultat);
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		 
		}

}
