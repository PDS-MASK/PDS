package view;

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

public class Home_view {

	private JFrame frame;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtV = new JTextField();
		txtV.setForeground(Color.BLACK);
		txtV.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		txtV.setBackground(SystemColor.menu);
		txtV.setText("Voici la liste des consommateurs du Centre Commercial : ");
		txtV.setBounds(12, 65, 377, 22);
		frame.getContentPane().add(txtV);
		txtV.setColumns(10);
		
		txtBienvenue = new JTextField();
		txtBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		txtBienvenue.setBounds(239, 13, 136, 25);
		txtBienvenue.setBackground(SystemColor.menu);
		txtBienvenue.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		txtBienvenue.setText("Bienvenue !");
		frame.getContentPane().add(txtBienvenue);
		txtBienvenue.setColumns(10);
		
		

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Verdana", Font.PLAIN, 14));
		table.setForeground(Color.WHITE);
		table.setBounds(12, 124, 584, 402);
		frame.getContentPane().add(table);
		
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
    	ResultSet rs = stmt.executeQuery("Select * from personne");
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
    	
    	JOptionPane.showMessageDialog(null,resultat);
    	
    	} catch (SQLException ed) {
    		
    		System.out.println("Connection Failed! Check output console");
    		ed.printStackTrace();
    		return;
    	}
    	
 	
    
    	showTableData();
    	
    	 table.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jTable1MouseClicked(evt);
             }

			private void jTable1MouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				 JOptionPane.showMessageDialog(null,table.getSelectedRow()+1);
			}
         });
		
	}
	
  	public void showTableData(){
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");
		String sql = "Select * from personne order by id_personne";
		Object pst = connection.prepareStatement(sql);
		Object rs = ((PreparedStatement) pst).executeQuery();
		table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
		System.out.print(" "+ rs);
		
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		 
		}

}


