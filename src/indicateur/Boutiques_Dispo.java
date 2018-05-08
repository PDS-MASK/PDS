package indicateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class Boutiques_Dispo extends JFrame {

	private JFrame frmBoutiquesDisponibles;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Boutiques_Dispo window = new Boutiques_Dispo();
					window.frmBoutiquesDisponibles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Boutiques_Dispo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBoutiquesDisponibles = new JFrame();
		frmBoutiquesDisponibles.setTitle("Boutiques disponibles");
		frmBoutiquesDisponibles.setBounds(100, 100, 450, 300);
		frmBoutiquesDisponibles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBoutiquesDisponibles.getContentPane().setLayout(null);
		String[] entete = {"Nom", "surface en m²"};
		Analyse a = new Analyse();
		List<Emplacement> l = new ArrayList<>();
		try {
			l = a.BoutiquesDispo();
			Object[][] data = new Object [l.size()][2];
			for(int i=0; i<l.size();i++) {
				System.out.println(i+"::::::"+l.get(i).getNom());
				data[i][0]=l.get(i).getNom();
				System.out.println("blaaaaaaaaaa"+"       "+data[i][0]);
				data[i][1]=l.get(i).getSurface();
				System.out.println("blaaaaaaaaaa"+"       "+data[i][1]);
				}
			table=new JTable(data,entete);
			table.setBounds(10, 240, 424, -228);
			table.setSurrendersFocusOnKeystroke(true);
			System.out.println("bibibibibibi"+"       "+data[0][0]);
			System.out.println("bobobobobobo"+"       "+data[1][0]);
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmBoutiquesDisponibles.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		//frmBoutiquesDisponibles.getContentPane().add(table_1);
		//frmBoutiquesDisponibles.pack();
		frmBoutiquesDisponibles.setVisible(true);
	}
}
