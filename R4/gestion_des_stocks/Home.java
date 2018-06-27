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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Home() throws SQLException, UnknownHostException, IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String ServerName = "localhost";
		int ServerPort = 9999;
		socket = new Socket(InetAddress.getByName(ServerName), ServerPort);
		Home.setOut(new PrintWriter(new OutputStreamWriter(Home.socket.getOutputStream()),true));
		Home.setIn(new BufferedReader(new InputStreamReader(Home.socket.getInputStream())));

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

		JButton btnMajStock = new JButton("Mise à jour des stocks");
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
					//send a msg to the server
					out.println("select_gestion_stock" + boutiqueSelectionnee);
					//return the data in msgrecu
					String msgrecu = msg();
					System.out.println("msg recu :   " + msgrecu);
					showTableData(msgrecu);
				} catch (IOException e) {
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
			String sql = "SELECT NOM_ARTICLE, CATEGORIE, QUANTITE FROM ARTICLE";
			Object pst = con.prepareStatement(sql);
			Object rs = ((PreparedStatement) pst).executeQuery();
			table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
			System.out.print(" " + rs);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}

	}
	
	private String msg() throws IOException{  
		String s = in.readLine();
		System.out.println("Messsage from server : " + s);
		if(s == null) {
			JOptionPane.showMessageDialog(null, "The server have been shutdown", "Connection error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return s;
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



	
}
