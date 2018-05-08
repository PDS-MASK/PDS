package indicateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class Boutiques_Dispo extends JFrame {

	private JFrame frmBoutiquesDisponibles;
	private JTable table;

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
		String[] b;
		String[] entetes = {"Nom", "surface"};
		table = new JTable();
		table.setFont(new Font("Sylfaen", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBounds(34, 108, 202, -31);
		Analyse a = new Analyse();
		List<Emplacement> l = new ArrayList<>();
		System.out.println("boutiques dispo");
		try {
			l = a.BoutiquesDispo();
			for(int i=0; i<l.size();i++) {
				System.out.println(i+"::::::"+l.get(i));
				}
			table = new JTable((TableModel) l);
		
			
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frmBoutiquesDisponibles.getContentPane().add(table);
	}
	
}
