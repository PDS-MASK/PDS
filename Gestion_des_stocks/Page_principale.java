package Gestion_des_stocks;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tables.Article;
import general.Administration;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Page_principale extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtBoutique;
	private JTextField txtArticle;
	private JTextField txtTaille;
	private JTextField txtCouleur;
	private JTextField textStock;
	Connection con = connect();
	ResultSet rs;
	PreparedStatement stat;
	Statement s;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page_principale frame1 = new Page_principale();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Connection connect() {
        try {
        	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pds", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur de connection a la base de donnees :" + e);
        }
        /*retourne un object con de type Connection*/
        return con;
    }

	/**
	 * Create the frame.
	 */
	public Page_principale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionDesStocks = new JLabel("Gestion des stocks");
		lblGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
		lblGestionDesStocks.setBounds(357, 5, 228, 41);
		contentPane.add(lblGestionDesStocks);

		JButton btnGestionDesBons = new JButton("Affichage des produits");
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consulter_stock();
			}
		});
		btnGestionDesBons.setBounds(239, 426, 315, 21);
		contentPane.add(btnGestionDesBons);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Administration a = new Administration();
				a.setVisible(true);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		JButton btnAjouterUnArticle = new JButton("Ajouter un article");
		btnAjouterUnArticle.setBounds(10, 390, 201, 21);
		contentPane.add(btnAjouterUnArticle);

		JButton btnSupprimerUnArticle = new JButton("Supprimer un article");
		btnSupprimerUnArticle.setBounds(10, 436, 201, 21);
		contentPane.add(btnSupprimerUnArticle);

		table = new JTable();
		table.setBounds(24, 157, 385, -106);
		contentPane.add(table);

		// // create a table model and set a Column Identifiers to this model
		// Object[] columns = {"Nom","Cat�gorie","Genre","Description"};
		// DefaultTableModel model = new DefaultTableModel();
		// model.setColumnIdentifiers(columns);
		//
		// // set the model to the table
		// table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table.setFont(font);
		table.setRowHeight(30);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(147, 56, 863, 324);

		getContentPane().setLayout(null);

		getContentPane().add(pane);

		JTextArea textBoutique = new JTextArea();
		textBoutique.setBounds(10, 101, 127, 22);
		contentPane.add(textBoutique);

		JTextArea textArticle = new JTextArea();
		textArticle.setBounds(10, 162, 127, 22);
		contentPane.add(textArticle);

		JTextArea textTaille = new JTextArea();
		textTaille.setBounds(10, 223, 127, 22);
		contentPane.add(textTaille);

		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(10, 297, 127, 22);
		contentPane.add(textDescription);

		txtBoutique = new JTextField();
		txtBoutique.setText("Nom boutique");
		txtBoutique.setBounds(10, 67, 127, 19);
		contentPane.add(txtBoutique);
		txtBoutique.setColumns(10);

		txtArticle = new JTextField();
		txtArticle.setText("Nom article");
		txtArticle.setColumns(10);
		txtArticle.setBounds(10, 133, 127, 19);
		contentPane.add(txtArticle);

		txtTaille = new JTextField();
		txtTaille.setText("Taille");
		txtTaille.setColumns(10);
		txtTaille.setBounds(10, 194, 127, 19);
		contentPane.add(txtTaille);

		txtCouleur = new JTextField();
		txtCouleur.setText("Couleur");
		txtCouleur.setColumns(10);
		txtCouleur.setBounds(10, 268, 127, 19);
		contentPane.add(txtCouleur);

		textStock = new JTextField();
		textStock.setText("Stock");
		textStock.setColumns(10);
		textStock.setBounds(10, 329, 127, 19);
		contentPane.add(textStock);

		JTextArea txtStock = new JTextArea();
		txtStock.setBounds(10, 358, 127, 22);
		contentPane.add(txtStock);


		// create an array of objects to set the row data
		Object[] row = new Object[4];

		// button add row
		btnAjouterUnArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				row[0] = textBoutique.getText();
				row[1] = textArticle.getText();
				row[2] = textTaille.getText();
				row[3] = textDescription.getText();
				
				Article a = new Article();
				rentrer_article(a);

			}
		});

		// button remove row
		btnSupprimerUnArticle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// i = the index of the selected row
				int i = table.getSelectedRow();
				if (i >= 0) {
					// remove a row from jtable
					// model.removeRow(i);
					//theQuery("DELETE * FROM SOUS_ARTICLE WHERE ID_ARTICLE=" + table.getEditingColumn());

				} else {
					System.out.println("Delete Error");
				}
			}
		});

		// // button update row
		// btnModifierUnArticle.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// // i = the index of the selected row
		// int i = table.getSelectedRow();
		//
		// if(i >= 0)
		// {
		// table.getModel().setValueAt(textNom.getText(), i, 0);
		// table.getModel().setValueAt(textCategorie.getText(), i, 1);
		// table.getModel().setValueAt(textGenre.getText(), i, 2);
		// table.getModel().setValueAt(textDescription.getText(), i, 3);
		// }
		// else{
		// System.out.println("Update Error");
		// }
		// }
		// });

		showTableData();

	}

	public void showTableData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ahamdi", "");
			//String sql = "SELECT NOM_BOUTIQUE, NOM_ARTICLE, TAILLE, COULEUR, STOCK FROM ARTICLE INNER JOIN SOUS_ARTICLE ON ARTICLE.ID_ARTICLE = SOUS_ARTICLE.ID_ARTICLE INNER JOIN BOUTIQUE ON BOUTIQUE.ID_BOUTIQUE = SOUS_ARTICLE.ID_BOUTIQUE";
			String sql = "SELECT * FROM ARTICLE";
			Object pst = connection.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			System.out.print(" " + rs);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}

	}
	
	 public Object[][] consulter_stock() {
	        Object[][] donnes = new Object[100][4];

	        try {
	            /*initialisation de l'object con de type Connection et etablissement de la connection*/
	            con = connect();
	            String r = "select * from Article_Stock";
	            /*initialisation de lobjet stat de type prepareStatement*/
	            s = con.createStatement();
	            rs = s.executeQuery(r);
	            int i = 0;
	            while (rs.next()) {
	                donnes[i][0] = rs.getString("ID_Article");
	                donnes[i][1] = rs.getString("Nom_Article");
	                donnes[i][2] = rs.getString("Categorie");
	                donnes[i][3] = rs.getString("Quantite");
	                i++;
	            }
	        } catch (Exception e) {
	        }
	        return donnes;
	    }
	 
	 public void rentrer_article(tables.Article a) {
	        /*recuperation des membres du bean Article deja rempli*/
	        int id_article = a.getId_Article();
	        String nom_article = a.getNom_Article();
	        String categorie = a.getCategorie();
	        int quantite = a.getQuantite();
	        try {
	            /*creation de la requete pour linsertion dans la table Article_Stock*/
	            stat = con.prepareStatement("insert into Article_Stock values (?,?,?,?)");
	            /*initialisation des valeur de lobjet prepareStatement avec les
	            valeures recuperéés a partir du bean Article accepté comme parametre dentree*/
	            stat.setInt(1, id_article);
	            stat.setString(2, nom_article);
	            stat.setString(3, categorie);
	            stat.setInt(4, quantite);
	            /*execution de la requete*/
	            int i = stat.executeUpdate();
	            if (i != 0) {
	                JOptionPane.showMessageDialog(null, "Article N°:" + id_article + "\nEn Stock ");

	            } else {
	                JOptionPane.showMessageDialog(null, "Erreur de traitement");
	            }
	            /*Creation de la requete pour supprimer la ligne recuperée de la
	            table Commande et inserée das la table Article_Stock*/
	            stat = con.prepareStatement("delete from Commande where ID_Com=?");
	            stat.setInt(1, id_article);
	            int ex = stat.executeUpdate();
	            if (ex != 0) {
	                JOptionPane.showMessageDialog(null, "Supression Article N°:" + id_article + "\nDe la table Commande");
	            } else {
	                JOptionPane.showMessageDialog(null, "Erreur de traitement");
	            }

	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    }
	 
	 

}