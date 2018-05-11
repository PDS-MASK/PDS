package parcourstype_view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;

public class Home_view {

	private JFrame frmPlateformeDesParcours;
	private JTextField txtBienvenue;
	private JTextField txtV;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_view window = new Home_view();
					window.frmPlateformeDesParcours.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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



		Personne p = new Personne();
		p.showTableData(table);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}

			private void jTable1MouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,table.getSelectedRow()+1);
				int selected_id_consumer =0;
				selected_id_consumer = table.getSelectedRow()+1;
				frmPlateformeDesParcours.setVisible(false);
				Information_customer customer = new Information_customer(selected_id_consumer);
				customer.initialize();
			}
		});

	}
}


