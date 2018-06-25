package Gestion_des_stocks;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class Tracking extends JFrame {

	private JPanel contentPane;
	private JTable table2;
	private JComboBox BoutiqueComboBox;
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
					Tracking frame = new Tracking();
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
	public Tracking() throws SQLException {
		setTitle("Suivi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1195, 933);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table2 = new JTable();
		table2.setBounds(24, 157, 385, -106);
		contentPane.add(table2);
		
		String nomBoutique;
		BoutiqueComboBox = new JComboBox();
		BoutiqueComboBox.setBounds(322, 21, 122, 26);
		contentPane.add(BoutiqueComboBox);
		Statement sta = con.createStatement();
		String Sql = "select nom_boutique from boutique";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			nomBoutique = rs.getString("nom_boutique");
			BoutiqueComboBox.addItem(nomBoutique);
		}
		
		JButton btnGestionDesBons = new JButton("Affichage du suivi");
		btnGestionDesBons.setBounds(461, 24, 233, 21);
		contentPane.add(btnGestionDesBons);
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showTableData();
			}
		});
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table2.setBackground(Color.LIGHT_GRAY);
		table2.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table2.setFont(font);
		table2.setRowHeight(30);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table2);
		pane.setBounds(39, 63, 1119, 839);

		getContentPane().setLayout(null);

		getContentPane().add(pane);
		
		JLabel lblSuivi = new JLabel("Suivi");
		lblSuivi.setBounds(233, 16, 69, 20);
		contentPane.add(lblSuivi);
		
		JLabel lblBoutique = new JLabel("Boutique");
		lblBoutique.setBounds(339, 0, 69, 20);
		contentPane.add(lblBoutique);
		showTableData();
	}



	public void showTableData() {
		try {
			String boutiqueSelectionnee = BoutiqueComboBox.getSelectedItem().toString();
			Statement staid = con.createStatement();
			String Sql2 = "select * from boutique where boutique.nom_boutique ='" + boutiqueSelectionnee + "'";
			ResultSet rs2 = staid.executeQuery(Sql2);
			rs2.next();
			String id_boutique = rs2.getString("id_boutique");
			String sql = "SELECT TYPE_ACTION,NOM_ARTICLE, NOM_FOURNISSEUR, DATE_ACTION, QUANTITE_ACTION, ANCIENNE_VALEUR, COMMENTAIRE FROM HISTORIQUE INNER JOIN FOURNISSEUR ON FOURNISSEUR.ID_FOURNISSEUR = HISTORIQUE.ID_FOURNISSEUR INNER JOIN ARTICLE ON ARTICLE.ID_ARTICLE = HISTORIQUE.ID_ARTICLE INNER JOIN BOUTIQUE ON BOUTIQUE.ID_BOUTIQUE = HISTORIQUE.ID_BOUTIQUE WHERE BOUTIQUE.ID_BOUTIQUE =" + id_boutique;
			System.out.println(sql);
			Object pst = con.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			System.out.println(rs);
			table2.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			System.out.print(" " + rs);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}

	}
}
