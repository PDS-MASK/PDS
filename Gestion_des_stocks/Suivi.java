package Gestion_des_stocks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class Suivi extends JFrame {

	private JPanel contentPane;
	private JTable table2;
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
					Suivi frame = new Suivi();
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
	public Suivi() {
		setTitle("Suivi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table2 = new JTable();
		table2.setBounds(24, 157, 385, -106);
		contentPane.add(table2);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table2.setBackground(Color.LIGHT_GRAY);
		table2.setForeground(Color.black);
		Font font = new Font("", 1, 22);
		table2.setFont(font);
		table2.setRowHeight(30);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table2);
		pane.setBounds(39, 63, 778, 475);

		getContentPane().setLayout(null);

		getContentPane().add(pane);
		
		JLabel lblSuivi = new JLabel("Suivi");
		lblSuivi.setBounds(233, 16, 69, 20);
		contentPane.add(lblSuivi);
		showTableData();
	}



	public void showTableData() {
		try {
			//Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ahamdi", "resident4");
			//String sql = "SELECT NOM_BOUTIQUE, NOM_ARTICLE, TAILLE, COULEUR, STOCK FROM ARTICLE INNER JOIN SOUS_ARTICLE ON ARTICLE.ID_ARTICLE = SOUS_ARTICLE.ID_ARTICLE INNER JOIN BOUTIQUE ON BOUTIQUE.ID_BOUTIQUE = SOUS_ARTICLE.ID_BOUTIQUE";
			String sql = "SELECT TYPE_ACTION, DATE_ACTION, QUANTITE_ACTION, COMMENTAIRE FROM HISTORIQUE";
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
