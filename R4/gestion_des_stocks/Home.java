package Gestion_des_stocks;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import general.Administration;
import net.proteanit.sql.DbUtils;
import server.PoolConnexion;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtBoutique;
	private static Socket socket;
	private static PrintWriter out ; // to send message to the client 
	private static BufferedReader in ; // to receive message from the client
	public PoolConnexion pool = new PoolConnexion();
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
					Home frame1 = new Home();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Connection connect() {
		try {
			con = pool.getConnection();
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
	public Home() throws SQLException {

		try {
			String ServerName = "localhost";
			int ServerPort = 9999;
			socket = new Socket(InetAddress.getByName(ServerName), ServerPort);
			Home.setOut(new PrintWriter(new OutputStreamWriter(Home.socket.getOutputStream()),true));
			Home.setIn(new BufferedReader(new InputStreamReader(Home.socket.getInputStream())));

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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


		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Administration a = new Administration();
				a.setVisible(true);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		JButton btnApproStock = new JButton("Approvisionner le stock");
		btnApproStock.setBounds(10, 390, 201, 21);
		contentPane.add(btnApproStock);

		JButton btnMajStock = new JButton("Mise � jour des stocks");
		btnMajStock.setBounds(10, 436, 201, 21);
		contentPane.add(btnMajStock);

		table = new JTable();
		table.setBounds(24, 157, 385, -106);
		contentPane.add(table);


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

		txtBoutique = new JTextField();
		txtBoutique.setText("Boutique");
		txtBoutique.setColumns(10);
		txtBoutique.setBounds(10, 64, 122, 19);
		contentPane.add(txtBoutique);

		JButton btnSuiviDesActivits = new JButton("Suivi des activit\u00E9s");
		btnSuiviDesActivits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_des_stocks.Tracking s;
				try {
					s = new Gestion_des_stocks.Tracking();
					s.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSuiviDesActivits.setBounds(724, 432, 187, 29);
		contentPane.add(btnSuiviDesActivits);

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

		JButton btnGestionDesBons = new JButton("Affichage des produits");
		btnGestionDesBons.setBounds(239, 426, 315, 21);
		contentPane.add(btnGestionDesBons);
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String boutiqueSelectionnee = BoutiqueComboBox.getSelectedItem().toString();
				System.out.println(boutiqueSelectionnee);
				Statement sta;
				try {
					Statement sta2 = con.createStatement();
					String Sql2 = "select * from boutique where boutique.nom_boutique ='" + boutiqueSelectionnee + "'";
					ResultSet rs2 = sta2.executeQuery(Sql2);
					rs2.next();
					String i = rs2.getString("id_boutique");
					//J'AFFICHE TOUT LE STOCK DE LA BOUTIQUE QUI A L'ID QUE JAI RECUP PRECEDEMMENT
					showTableData(i);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});



		// button add row
		btnApproStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Gestion_des_stocks.Provisioning as;
				try {
					as = new Gestion_des_stocks.Provisioning();
					as.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});

		// button remove row
		btnMajStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Gestion_des_stocks.Inventory_update ms;
				try {
					ms = new Gestion_des_stocks.Inventory_update();
					ms.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});



		showTableData();

	}

	public void showTableData() {
		try {
			//Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ahamdi", "resident4");
			//String sql = "SELECT NOM_BOUTIQUE, NOM_ARTICLE, TAILLE, COULEUR, STOCK FROM ARTICLE INNER JOIN SOUS_ARTICLE ON ARTICLE.ID_ARTICLE = SOUS_ARTICLE.ID_ARTICLE INNER JOIN BOUTIQUE ON BOUTIQUE.ID_BOUTIQUE = SOUS_ARTICLE.ID_BOUTIQUE";
			String sql = "SELECT NOM_ARTICLE, CATEGORIE, QUANTITE FROM ARTICLE";
			Object pst = con.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			System.out.print(" " + rs);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}

	}

	public static void setIn(BufferedReader in) {
		Home.in = in;
	}

	public static void setOut(PrintWriter out) {
		Home.out = out;
	}

	public void showTableData(String s) {
		try {
			String sql = "SELECT NOM_ARTICLE, CATEGORIE, QUANTITE FROM ARTICLE WHERE ARTICLE.ID_BOUTIQUE = " + s;
			Object pst = con.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			System.out.print(" " + rs);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}

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
				JOptionPane.showMessageDialog(null, "Suppression Article N°:" + id_article + "\nDe la table Commande");
			} else {
				JOptionPane.showMessageDialog(null, "Erreur de traitement");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}