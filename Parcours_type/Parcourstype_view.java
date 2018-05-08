package parcourstype_view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JComboBox;

public class itinerary_view {

	private JFrame frame;
	private JTextField txtIntineraryInterface;
	private JTextField txtCombienDeMagasins;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					itinerary_view window = new itinerary_view();
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
	public itinerary_view() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 742, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtIntineraryInterface = new JTextField();
		txtIntineraryInterface.setBackground(SystemColor.menu);
		txtIntineraryInterface.setBounds(240, 13, 232, 22);
		txtIntineraryInterface.setText("Interface des propositions de parcours");
		frame.getContentPane().add(txtIntineraryInterface);
		txtIntineraryInterface.setColumns(10);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnParcoursParPopularit = new JRadioButton("Parcours par rapport \u00E0 vos gouts");
		rdbtnParcoursParPopularit.setBounds(32, 106, 245, 25);
		frame.getContentPane().add(rdbtnParcoursParPopularit);
		
		JRadioButton rdbtnParcoursParLocalisation = new JRadioButton("Parcours par Localisation");
		rdbtnParcoursParLocalisation.setBounds(32, 142, 245, 25);
		frame.getContentPane().add(rdbtnParcoursParLocalisation);
		
		bg.add(rdbtnParcoursParPopularit);
		bg.add(rdbtnParcoursParLocalisation);
		
		txtCombienDeMagasins = new JTextField();
		txtCombienDeMagasins.setText("Combien de magasins voulez-vous que l'on vous propose ? ");
		txtCombienDeMagasins.setBounds(32, 191, 346, 22);
		frame.getContentPane().add(txtCombienDeMagasins);
		txtCombienDeMagasins.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(405, 191, 37, 34);
		for (int i=1;i<11;i++)
			
		{
			comboBox.addItem(i);
			
			
		}
		frame.getContentPane().add(comboBox);
	}
}
