package Gestion_des_stocks;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import general.Administration;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class Provisioning extends JFrame {

	private JPanel contentPane;
	private JTextField txtArticle;
	private JTextField txtCategorie;
	private JTextField txtQuantit;
	Connection con = connect();
	private JTextField txtCommentaire;
	private static Provisioning frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Provisioning();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Connection connect() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ahamdi", "pds123");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur de connection a la base de donnees :" + e);
		}
		/*retourne un object con de type Connection*/
		return con;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Provisioning() throws SQLException {
		setTitle("Approvisionnement du stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtArticle = new JTextField();
		txtArticle.setText("Article");
		txtArticle.setBounds(176, 59, 123, 26);
		contentPane.add(txtArticle);
		txtArticle.setColumns(10);

		JTextArea textArticle = new JTextArea();
		textArticle.setBounds(176, 87, 123, 22);
		contentPane.add(textArticle);

		txtCategorie = new JTextField();
		txtCategorie.setText("Categorie");
		txtCategorie.setColumns(10);
		txtCategorie.setBounds(176, 125, 123, 26);
		contentPane.add(txtCategorie);

		JTextArea textCategorie = new JTextArea();
		textCategorie.setBounds(176, 152, 123, 22);
		contentPane.add(textCategorie);

		txtQuantit = new JTextField();
		txtQuantit.setText("Quantit\u00E9");
		txtQuantit.setColumns(10);
		txtQuantit.setBounds(176, 188, 123, 26);
		contentPane.add(txtQuantit);

		JTextArea textQuantite = new JTextArea();
		textQuantite.setBounds(176, 220, 123, 22);
		contentPane.add(textQuantite);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		String nomBoutique;
		JComboBox BoutiqueComboBox = new JComboBox();
		BoutiqueComboBox.setBounds(10, 99, 122, 26);
		contentPane.add(BoutiqueComboBox);
		Statement sta = con.createStatement();
		String Sql = "select nom_boutique from boutique";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			nomBoutique = rs.getString("nom_boutique");
			BoutiqueComboBox.addItem(nomBoutique);
		}
		
		String nomFournisseur;
		JComboBox FournisseurcomboBox = new JComboBox();
		FournisseurcomboBox.setBounds(10, 188, 122, 26);
		contentPane.add(FournisseurcomboBox);
		Statement stab = con.createStatement();
		String Sqlb = "select nom_fournisseur from fournisseur";
		ResultSet rsb = stab.executeQuery(Sqlb);
		while (rsb.next()) {
			nomFournisseur = rsb.getString("nom_fournisseur");
			FournisseurcomboBox.addItem(nomFournisseur);
		}
		
		txtCommentaire = new JTextField();
		txtCommentaire.setText("Commentaire");
		txtCommentaire.setColumns(10);
		txtCommentaire.setBounds(334, 45, 123, 26);
		contentPane.add(txtCommentaire);
		
		JTextArea textCommentaire = new JTextArea();
		textCommentaire.setBounds(334, 87, 164, 87);
		contentPane.add(textCommentaire);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date actuelle = new Date();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String dat = dateFormat.format(actuelle);
					String nomArticle = textArticle.getText();
					String categorieArticle = textCategorie.getText();
					String quantiteArticle = textQuantite.getText();
					String commentaire = textCommentaire.getText();
					String boutiqueSelectionnee = BoutiqueComboBox.getSelectedItem().toString();
					String fournisseurSelectionnee = FournisseurcomboBox.getSelectedItem().toString();
					//System.out.println(boutiqueSelectionnee);
					Statement staid = con.createStatement();
					String Sql2 = "select * from boutique where boutique.nom_boutique ='" + boutiqueSelectionnee + "'";
					ResultSet rs2 = staid.executeQuery(Sql2);
					rs2.next();
					String id_boutique = rs2.getString("id_boutique");
					Statement sta2 = con.createStatement();
					//System.out.println(id_boutique+nomArticle+categorieArticle+quantiteArticle);
					
					Statement stafour = con.createStatement();
					String Sqlfour = "select * from fournisseur where fournisseur.nom_fournisseur ='" + fournisseurSelectionnee + "'";
					ResultSet rsfour = stafour.executeQuery(Sqlfour);
					rsfour.next();
					String id_four = rsfour.getString("id_fournisseur");
					//System.out.println(id_boutique+nomArticle+categorieArticle+quantiteArticle);

					String Sql3 = "insert into article (id_article, nom_article,categorie,quantite,id_boutique) values ((select max(ID_article)+1 from ARTICLE),"+ "'" + nomArticle + "'" + ',' + "'" + categorieArticle + "'" + ',' + quantiteArticle + ',' + id_boutique + ')';
					//System.out.println(Sql3);
					ResultSet rs3 = sta2.executeQuery(Sql3);
					rs3.next();
					
					Statement sta3 = con.createStatement();
					//System.out.println("nom de l'article" + nomArticle);
					Statement staid_article = con.createStatement();
					String Sqlid_article = "select * from article where article.nom_article ='" + nomArticle + "'";
					ResultSet rs4 = staid_article.executeQuery(Sqlid_article);
					//System.out.println(Sqlid_article);
					rs4.next();
					String id_article = rs4.getString("id_article");
					//System.out.println(id_article);


					String Sql5 = "insert into historique (id_historique, type_action, date_action, quantite_action, ancienne_valeur, commentaire, id_article, id_boutique, id_fournisseur) values ((select max(ID_historique)+1 from historique)," + "'" + "Approvisionnement" + "'" + "," + "'" + dat+ "'"+ ',' + quantiteArticle + ',' + "0" + ',' + "'" + commentaire+ "'" + "," + id_article + "," + id_boutique + "," + id_four + ')' ;
					System.out.println(Sql5);
					Statement sta5 = con.createStatement();
					ResultSet rs5 = sta5.executeQuery(Sql5);
					rs5.next();
					setVisible(false);
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "erreur de saisie");
					//ee.printStackTrace();
				}
			}
		});
		btnValider.setBounds(176, 258, 115, 29);
		contentPane.add(btnValider);
		
		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setBounds(16, 152, 116, 20);
		contentPane.add(lblFournisseur);
		
		JLabel lblBoutique = new JLabel("Boutique");
		lblBoutique.setBounds(10, 62, 122, 20);
		contentPane.add(lblBoutique);
		
	
		
	
		
		
	}
}
