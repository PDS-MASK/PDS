package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Parcourstype_view {

	private JFrame frmPlateformeDesParcours;
	private JTextField txtLeConsommateurQui;
	private JTextField Name_Selected_Cons;
	private int id_consumer;
	private JTextField txtCettePersonnePossde;
	private JTextField SelectedProfilCons;
	private JTextField txtVosHabitudesDachats;
	private JTextField SelectedProfilPurchasePreference;
	
	public Parcourstype_view(int i)
	{
		
		this.id_consumer = i;
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parcourstype_view window = new Parcourstype_view();
					window.frmPlateformeDesParcours.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Parcourstype_view() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frmPlateformeDesParcours = new JFrame();
		frmPlateformeDesParcours.setTitle("Plateforme des parcours type");
		frmPlateformeDesParcours.setBounds(100, 100, 741, 633);
		frmPlateformeDesParcours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlateformeDesParcours.getContentPane().setLayout(null);
		
		txtLeConsommateurQui = new JTextField();
		txtLeConsommateurQui.setEditable(false);
		txtLeConsommateurQui.setBackground(SystemColor.menu);
		txtLeConsommateurQui.setText("Le consommateur qui a \u00E9t\u00E9 selectionn\u00E9 est :");
		txtLeConsommateurQui.setBounds(12, 76, 263, 22);
		frmPlateformeDesParcours.getContentPane().add(txtLeConsommateurQui);
		txtLeConsommateurQui.setColumns(10);
		
		Name_Selected_Cons = new JTextField();
		Name_Selected_Cons.setEditable(false);
		Name_Selected_Cons.setBackground(SystemColor.menu);
		Name_Selected_Cons.setBounds(287, 76, 200, 22);
		frmPlateformeDesParcours.getContentPane().add(Name_Selected_Cons);
		Name_Selected_Cons.setColumns(10);
		
		txtCettePersonnePossde = new JTextField();
		txtCettePersonnePossde.setText("Cette personne poss\u00E8de le(s) profil(s) suivant(s) :");
		txtCettePersonnePossde.setEditable(false);
		txtCettePersonnePossde.setColumns(10);
		txtCettePersonnePossde.setBackground(SystemColor.menu);
		txtCettePersonnePossde.setBounds(12, 142, 294, 22);
		frmPlateformeDesParcours.getContentPane().add(txtCettePersonnePossde);
		
		SelectedProfilCons = new JTextField();
		SelectedProfilCons.setEditable(false);
		SelectedProfilCons.setColumns(10);
		SelectedProfilCons.setBackground(SystemColor.menu);
		SelectedProfilCons.setBounds(318, 142, 246, 77);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilCons);
		
		txtVosHabitudesDachats = new JTextField();
		txtVosHabitudesDachats.setText("Vos habitudes d'achats sont : ");
		txtVosHabitudesDachats.setEditable(false);
		txtVosHabitudesDachats.setColumns(10);
		txtVosHabitudesDachats.setBackground(SystemColor.menu);
		txtVosHabitudesDachats.setBounds(12, 341, 180, 22);
		frmPlateformeDesParcours.getContentPane().add(txtVosHabitudesDachats);
		
		SelectedProfilPurchasePreference = new JTextField();
		SelectedProfilPurchasePreference.setEditable(false);
		SelectedProfilPurchasePreference.setColumns(10);
		SelectedProfilPurchasePreference.setBackground(SystemColor.menu);
		SelectedProfilPurchasePreference.setBounds(206, 326, 358, 69);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilPurchasePreference);
		frmPlateformeDesParcours.setVisible(true);
	}
	
	public void showSelectedIdCons(int i){
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
		String sql = "Select NOM_PERSONNE,PRENOM_PERSONNE from PERSONNE where id_personne ="+i;
		Object pst = connection.prepareStatement(sql);
		Object rs = ((PreparedStatement) pst).executeQuery();
		String resultat = " ";
		System.out.print(" "+ rs);
		 while(((ResultSet) rs).next()){
			  resultat = ((ResultSet) rs).getString("nom_personne")+((ResultSet) rs).getString("prenom_personne");
			 }
		 Name_Selected_Cons.setText(resultat);
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		 
		}
	
	public void showSelectedConsProfil(int i){
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
	
	public void showSelectedConsPurchasePreference(int i)
	{
		
			try{
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
			String sql = "select nom_souscategorie from sous_categorie , categorie_personne where sous_categorie.id_souscategorie = categorie_personne.ID_SOUSCATEGORIE and categorie_personne.ID_PERSONNE ="+i;
			Object pst = connection.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			String resultat = " ";
			System.out.print(" "+ rs);
			 while(((ResultSet) rs).next()){
				  resultat = resultat+((ResultSet) rs).getString(i);
				 }
			 SelectedProfilPurchasePreference.setText(resultat);
			}
			catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			 
			}
			 
			
		
		
	}

	public JFrame getFrame() {
		return frmPlateformeDesParcours;
	}

	public void setFrame(JFrame frame) {
		this.frmPlateformeDesParcours = frame;
	}

	public JTextField getTxtLeConsommateurQui() {
		return txtLeConsommateurQui;
	}

	public void setTxtLeConsommateurQui(JTextField txtLeConsommateurQui) {
		this.txtLeConsommateurQui = txtLeConsommateurQui;
	}

	public JTextField getName_Selected_Cons() {
		return Name_Selected_Cons;
	}

	public void setName_Selected_Cons(JTextField name_Selected_Cons) {
		Name_Selected_Cons = name_Selected_Cons;
	}

	public int getId_consumer() {
		return id_consumer;
	}

	public void setId_consumer(int id_consumer) {
		this.id_consumer = id_consumer;
	}
	
	
	
	
	
}
