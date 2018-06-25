package parcourstype_view;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class itinerary_view {

	private JFrame frame;
	private JTextField txtIntineraryInterface;
	private JTextField txtCombienDeMagasins;
	private String word;
	protected Object Sous_Article;

	
	public itinerary_view(String word)
	
	{
		this.word = word;
		
	}
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
	void initialize() {
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
		JComboBox comboBox = new JComboBox();
		
		rdbtnParcoursParPopularit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtCombienDeMagasins = new JTextField();
				txtCombienDeMagasins.setBackground(SystemColor.menu);
				txtCombienDeMagasins.setText("Que cherchez-vous comme article ? ");
				txtCombienDeMagasins.setBounds(32, 269, 346, 22);
				frame.getContentPane().add(txtCombienDeMagasins);
				txtCombienDeMagasins.setColumns(10);

				
				String[] resultat = word.split("\\s+");
				
				for (int i=1;i<resultat.length;i++)

				{
					comboBox.addItem(resultat[i]);
					System.out.println(resultat[i]);


				}
				comboBox.setBounds(406, 263, 37, 34);
			
				frame.getContentPane().add(comboBox);

			}
		});


	

		JButton btnGo = new JButton("Go !");
		//itinerary_view itinerary = new itinerary_view();
		Sous_Article sa = new Sous_Article();
		
		btnGo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int getNumberComboBox;
				getNumberComboBox = comboBox.getSelectedIndex()+1;
				String getContentComboBox = comboBox.getSelectedItem().toString();
				JOptionPane.showMessageDialog(null,getContentComboBox);
				JOptionPane.showMessageDialog(null,getNumberComboBox);
				
				if (rdbtnParcoursParPopularit.isSelected())
					 
					sa.ShowItineraryByPreference(getNumberComboBox,getContentComboBox);
					
			}
		});
		btnGo.setBounds(557, 268, 97, 25);
		frame.getContentPane().add(btnGo);
		frame.setVisible(true);


	}

	
}
