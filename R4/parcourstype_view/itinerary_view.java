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
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class itinerary_view {

	private JFrame frame;
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
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 742, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnParcoursParPopularit = new JRadioButton("Parcours par rapport \u00E0 vos gouts");
		rdbtnParcoursParPopularit.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnParcoursParPopularit.setBounds(409, 65, 245, 25);
		frame.getContentPane().add(rdbtnParcoursParPopularit);

		JRadioButton rdbtnParcoursParLocalisation = new JRadioButton("Parcours par Localisation");
		rdbtnParcoursParLocalisation.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnParcoursParLocalisation.setBounds(409, 107, 245, 25);
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
		btnGo.setBounds(469, 465, 97, 25);
		frame.getContentPane().add(btnGo);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 280, 648);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPl = new JLabel("Plateforme des parcours-type");
		lblPl.setForeground(SystemColor.inactiveCaptionBorder);
		lblPl.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPl.setBounds(33, 286, 216, 37);
		panel.add(lblPl);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\BEN MALEK Mohamed\\Downloads\\Mask_Logo_2.png"));
		lblNewLabel.setBounds(60, 97, 152, 83);
		panel.add(lblNewLabel);
		frame.setVisible(true);


	}

	
}
