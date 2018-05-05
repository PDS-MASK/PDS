package Gestion_des_stocks;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import general.Administration;

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
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","resident4");	
			Statement stmt = null;
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(query);
			ResultSetMetaData resultMeta = result.getMetaData();

			while(result.next())
			{          
				for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
					System.out.print(result.getObject(i).toString());  
			}
			result.close();
			stmt.close();
			JOptionPane.showMessageDialog(null,"Query Executed");

			/*
			 *  String  sql = "SELECT adresse FROM customer WHERE login = " + login;
    //System.out.println(sql);

    Statement state = _conn.createStatement();
    ResultSet result = state.executeQuery(sql);
    ResultSetMetaData resultMeta = result.getMetaData();

    while(result.next())
    {          
        for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            System.out.print(result.getObject(i).toString());  
    }
    result.close();
        state.close();
			 */
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public Gestion_des_stocks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestionDesStocks = new JLabel("Gestion des stocks");
		lblGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
		lblGestionDesStocks.setBounds(283, 10, 228, 41);
		contentPane.add(lblGestionDesStocks);

		JButton btnGestionDesBons = new JButton("Test affichage des produits");
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theQuery("SELECT * FROM ARTICLE");
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
		btnSupprimerUnArticle.setBounds(585, 390, 201, 21);
		contentPane.add(btnSupprimerUnArticle);

		table = new JTable();
		table.setBounds(24, 157, 385, -106);
		contentPane.add(table);

		// create a table model and set a Column Identifiers to this model 
		Object[] columns = {"Nom","Catégorie","Genre","Description"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);

		// set the model to the table
		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("",1,22);
		table.setFont(font);
		table.setRowHeight(30);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 58, 776, 322);

		getContentPane().setLayout(null);

		getContentPane().add(pane);



	}
}
