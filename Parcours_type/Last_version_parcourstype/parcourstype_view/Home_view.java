package parcourstype_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import server.ClientProcessing;

public class Home_view {

	private static JFrame frmPlateformeDesParcours;
	private JTextField txtBienvenue;
	private JTextField txtV;
	private static JTable table;
	private static Socket socket;
	private static PrintWriter out ; // to send message to the client 
	private static BufferedReader in ; // to receive message to the client
	private static int selected_id_consumer = 1;
	private static Information_customer customer;
	
	
	
	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		Home_view.socket = socket;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_view window = new Home_view();
					window.frmPlateformeDesParcours.setVisible(true);
					Personne p = new Personne();
					p.showTableData(table);

					table.addMouseListener(new java.awt.event.MouseAdapter() {
						public void mouseClicked(java.awt.event.MouseEvent evt) {
							try {
								jTable1MouseClicked(evt);
								
								frmPlateformeDesParcours.setVisible(false);
								
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						private void jTable1MouseClicked(MouseEvent evt) throws IOException {
							// TODO Auto-generated method stub
							Home_view.setSelected_id_consumer(table.getSelectedRow()+1);
							//selected_id_consumer = table.getSelectedRow()+1;
							JOptionPane.showMessageDialog(null,Home_view.getSelected_id_consumer());

							 customer = new Information_customer(Home_view.getSelected_id_consumer());
							customer.initialize();
							
							
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home_view() {
		initialize("localhost",9999);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String ServerName, int ServerPort) {
		
		try {
			socket = new Socket(InetAddress.getByName(ServerName), ServerPort);
			this.setOut(new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()),true));
			this.setIn(new BufferedReader(new InputStreamReader(this.socket.getInputStream())));
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		frmPlateformeDesParcours = new JFrame();
		frmPlateformeDesParcours.getContentPane().setBackground(SystemColor.menu);
		frmPlateformeDesParcours.setTitle("Plateforme des parcours type");
		frmPlateformeDesParcours.setBounds(100, 100, 629, 595);
		frmPlateformeDesParcours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlateformeDesParcours.getContentPane().setLayout(null);

		txtV = new JTextField();
		txtV.setForeground(Color.BLACK);
		txtV.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		txtV.setBackground(SystemColor.menu);
		txtV.setText("Veuillez selectionner un des consommateurs du Centre Commercial : ");
		txtV.setBounds(12, 65, 451, 22);
		frmPlateformeDesParcours.getContentPane().add(txtV);
		txtV.setColumns(10);

		txtBienvenue = new JTextField();
		txtBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		txtBienvenue.setBounds(240, 13, 136, 25);
		txtBienvenue.setBackground(SystemColor.menu);
		txtBienvenue.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		txtBienvenue.setText("Bienvenue !");
		frmPlateformeDesParcours.getContentPane().add(txtBienvenue);
		txtBienvenue.setColumns(10);



		table = new JTable();
		table.setBackground(SystemColor.menu);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Verdana", Font.PLAIN, 14));
		table.setForeground(SystemColor.desktop);
		table.setBounds(12, 133, 584, 402);
		frmPlateformeDesParcours.getContentPane().add(table);

		Connection connection = null;

		try {


			try {
				Class.forName ("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}


			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");	
			Statement stmt = null;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select nom_personne,prenom_personne from personne");
			ResultSetMetaData rsmd = rs.getMetaData();

			int rowsSubmit = 0;
			String resultat = " ";
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {

				rowsSubmit++;

				for (int i = 1; i<= columnsNumber; i++)

				{
					resultat = resultat +" "+ rs.getString(i);

					System.out.print(" "+ rs.getString(i));

				}
				System.out.println();
			}

		} catch (SQLException ed) {

			System.out.println("Connection Failed! Check output console");
			ed.printStackTrace();
			return;
		}





	}

	public static int getSelected_id_consumer() {
		return selected_id_consumer;
	}

	public static void setSelected_id_consumer(int selected_id_consumer) {
		Home_view.selected_id_consumer = selected_id_consumer;
	}

	public static PrintWriter getOut() {
		return out;
	}

	public static void setOut(PrintWriter out) {
		Home_view.out = out;
	}

	public static BufferedReader getIn() {
		return in;
	}

	public static void setIn(BufferedReader in) {
		Home_view.in = in;
	}
}


