package Gestion_des_stocks;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Approvisionnement_stock extends JFrame {

	private JPanel contentPane;
	private JTextField txtArticle;
	private JTextField txtCategorie;
	private JTextField txtQuantit;
	Connection con = connect();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Approvisionnement_stock frame = new Approvisionnement_stock();
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
	 */
	public Approvisionnement_stock() {
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
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nomArticle = txtArticle.getText();
					String categorieArticle = txtCategorie.getText();
					String quantiteArticle = txtQuantit.getText();
					String idBoutique = s;
					Statement sta2 = con.createStatement();
					String Sql2 = "insert into article (id_article, nom_article,categorie,quantite,id_boutique) values ((select max(ID_article)+1 from ARTICLE),"+ nomArticle + ',' + categorieArticle + ',' + quantiteArticle + ',' + idBoutique + ')';
					ResultSet rs2 = sta2.executeQuery(Sql2);
					rs2.next();
					
				} catch (SQLException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			}
		});
		btnValider.setBounds(176, 258, 115, 29);
		contentPane.add(btnValider);
	}
}
