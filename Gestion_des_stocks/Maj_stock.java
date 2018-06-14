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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Maj_stock extends JFrame {

	private JPanel contentPane;
	private JTextField qteTextField;
	private Connection con2 = connect();
	private JTextField textFieldarticle;
	private JTextField textFieldcommentaire;
	private static Maj_stock frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Maj_stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Maj_stock() throws SQLException {
		setTitle("Mise \u00E0 jour des stocks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBoxBoutique = new JComboBox();
		comboBoxBoutique.setBounds(15, 64, 111, 26);
		contentPane.add(comboBoxBoutique);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		String nomBoutique;
		Statement sta2 = con2.createStatement();
		String Sql2 = "select nom_boutique from boutique";
		ResultSet rs2 = sta2.executeQuery(Sql2);
		while (rs2.next()) {
			nomBoutique = rs2.getString("nom_boutique");
			comboBoxBoutique.addItem(nomBoutique);
		}



		JLabel lblArticle = new JLabel("Article");
		lblArticle.setBounds(15, 102, 111, 20);
		contentPane.add(lblArticle);



		JLabel lblBoutique = new JLabel("Boutique");
		lblBoutique.setBounds(15, 37, 111, 20);
		contentPane.add(lblBoutique);

		JLabel lblCommentaire = new JLabel("Commentaire");
		lblCommentaire.setBounds(198, 102, 146, 20);
		contentPane.add(lblCommentaire);

		textFieldcommentaire = new JTextField();
		textFieldcommentaire.setColumns(10);
		textFieldcommentaire.setBounds(198, 138, 115, 65);
		contentPane.add(textFieldcommentaire);

		qteTextField = new JTextField();
		qteTextField.setBounds(198, 255, 78, 26);
		contentPane.add(qteTextField);
		qteTextField.setColumns(10);

		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setBounds(198, 219, 146, 20);
		contentPane.add(lblQuantite);

		textFieldarticle = new JTextField();
		textFieldarticle.setBounds(15, 138, 111, 26);
		contentPane.add(textFieldarticle);
		textFieldarticle.setColumns(10);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String boutiqueSelectionnee = comboBoxBoutique.getSelectedItem().toString();
				String articleSelectionne = textFieldarticle.getText();
				String quantite = qteTextField.getText();
				String commentaire = textFieldcommentaire.getText(); 
				Date actuelle = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dat = dateFormat.format(actuelle);

				Statement staid;
				try {
					staid = con2.createStatement();

					String Sql2 = "select * from boutique where boutique.nom_boutique ='" + boutiqueSelectionnee + "'";
					ResultSet rs2 = staid.executeQuery(Sql2);
					rs2.next();
					String id_boutique = rs2.getString("id_boutique");
					Statement sta2 = con2.createStatement();

					Statement sta3 = con2.createStatement();
					Statement staid_article = con2.createStatement();
					String Sqlid_article = "select * from article where article.nom_article ='" + articleSelectionne + "'";
					ResultSet rs4 = staid_article.executeQuery(Sqlid_article);
					rs4.next();
					String id_article = rs4.getString("id_article");

					Statement staq = con2.createStatement();
					String Sqlq = "select quantite from article where article.nom_article ='" + articleSelectionne + "'" + "AND " + "article.id_boutique =" + id_boutique;
					//System.out.println(Sqlq);
					ResultSet rsq = staq.executeQuery(Sqlq);
					rsq.next();
					int qte_prec = rsq.getInt("quantite");
					
					int newQuantite = Integer.parseInt(quantite);
					String Sql5;
					
					if (qte_prec > newQuantite) {
						Sql5 = "insert into historique (id_historique, type_action, date_action, quantite_action, ancienne_valeur, commentaire, id_article, id_boutique, id_fournisseur) values ((select max(ID_historique)+1 from historique)," + "'" + "Vente" + "'" + "," + "'" + dat+ "'"+ ',' + quantite + ',' + qte_prec + ',' + "'" + commentaire+ "'" + "," + id_article + "," + id_boutique + "," + "3" + ')' ;
						
					} else if (qte_prec < newQuantite) {
						Sql5 = "insert into historique (id_historique, type_action, date_action, quantite_action, ancienne_valeur,commentaire, id_article, id_boutique, id_fournisseur) values ((select max(ID_historique)+1 from historique)," + "'" + "Approvisionnement" + "'" + "," + "'" + dat+ "'"+ ',' + quantite + ',' + qte_prec +  ',' + "'" + commentaire+ "'" + "," + id_article + "," + id_boutique + "," + "3" + ')' ;
					} else {
						Sql5 = "insert into historique (id_historique, type_action, date_action, quantite_action, ancienne_valeur,commentaire, id_article, id_boutique, id_fournisseur) values ((select max(ID_historique)+1 from historique)," + "'" + "Roulement" + "'" + "," + "'" + dat+ "'"+ ',' + quantite + ',' + qte_prec + ','  + "'" + commentaire+ "'" + "," + id_article + "," + id_boutique + "," + "3" + ')' ;
					}
					
					System.out.println(Sql5);
					Statement staupdate = con2.createStatement();
					//requête à valider
					String Sqlupdate = "UPDATE ARTICLE SET QUANTITE =" + quantite + "WHERE ARTICLE.ID_ARTICLE =" + id_article + "AND ARTICLE.ID_BOUTIQUE = " + id_boutique;
					ResultSet rsupdate = staupdate.executeQuery(Sqlupdate);
					rsupdate.next();

					
					
					System.out.println(Sql5);
					Statement sta5 = con2.createStatement();
					ResultSet rs5 = sta5.executeQuery(Sql5);
					rs5.next();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(frame, "erreur de saisie");
					e1.printStackTrace();
					
				}
			}
		});
		btnValider.setBounds(198, 297, 115, 29);
		contentPane.add(btnValider);

	}

	public Connection connect() {
		try {
			con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ahamdi", "pds123");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur de connection a la base de donnees :" + e);
		}
		/*retourne un object con de type Connection*/
		return con2;
	}
}
