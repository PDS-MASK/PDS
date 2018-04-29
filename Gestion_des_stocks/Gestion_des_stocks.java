package Gestion_des_stocks;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import general.Administration;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Gestion_des_stocks extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_des_stocks frame = new Gestion_des_stocks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void theQuery(String query){
		Connection connection = null;
		
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","password");	
		Statement stmt = null;
		stmt = connection.createStatement();
	    stmt.executeUpdate(query);
	    JOptionPane.showMessageDialog(null,"Query Executed");
	    
	      }catch(Exception ex){
	          ex.printStackTrace();
	      }
	  }

	/**
	 * Create the frame.
	 */
	public Gestion_des_stocks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionDesStocks = new JLabel("Gestion des stocks");
		lblGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
		lblGestionDesStocks.setBounds(102, 10, 228, 41);
		contentPane.add(lblGestionDesStocks);

		JButton btnGestionDesBons = new JButton("Gestion des bons de livraison");
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theQuery("SELECT * FROM ARTICLE");		
			}
		});
		btnGestionDesBons.setBounds(66, 230, 315, 21);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 416, -113);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAjouterUnArticle = new JButton("Ajouter un article");
		btnAjouterUnArticle.setBounds(0, 198, 120, 21);
		contentPane.add(btnAjouterUnArticle);
		
		JButton btnSupprimerUnArticle = new JButton("Supprimer un article");
		btnSupprimerUnArticle.setBounds(146, 198, 140, 21);
		contentPane.add(btnSupprimerUnArticle);
		

		
	}
}
