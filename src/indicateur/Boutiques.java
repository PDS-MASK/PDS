package indicateur;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Boutiques extends JFrame{

	private JFrame frmBoutique;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Boutiques window = new Boutiques();
					window.frmBoutique.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Boutiques() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBoutique = new JFrame();
		frmBoutique.setTitle("Boutique");
		frmBoutique.setBounds(100, 100, 450, 300);
		frmBoutique.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBoutique.getContentPane().setLayout(null);
		JButton retour = new JButton("retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBoutique.setVisible(false);
				Indicateur c = new Indicateur();
				//c.setVisible(true);
			}
		});
		retour.setBounds(350, 230, 75, 20);
		frmBoutique.add(retour);
		//JLabel boutiques_dispo = new JLabel("");
		JButton btnNewButton = new JButton("Emplacements Disponibles");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boutiques_Dispo b = new Boutiques_Dispo();
				b.setVisible(true);
				//frmBoutique.setVisible(false);
			}
		});
		btnNewButton.setBounds(100, 69, 250, 39);
		frmBoutique.getContentPane().add(btnNewButton);
		
		JButton btnVisites = new JButton("Visites");
		btnVisites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client c = new Client();
				c.setVisible(true);
				//frmBoutique.setVisible(false);
			}
		});
		btnVisites.setBounds(100, 123, 250, 39);
		frmBoutique.getContentPane().add(btnVisites);
		
		JButton btnAchats = new JButton("Achats");
		btnAchats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Achat a = new Achat();
				a.setVisible(true);
				//frmBoutique.setVisible(false);
			}
		});
		btnAchats.setBounds(100, 173, 250, 39);
		frmBoutique.getContentPane().add(btnAchats);
//		boutiques_dispo.setBounds(235, 66, 136, 32);
//		//frmBoutique.getContentPane().add(boutiques_dispo);
//		boutiques_dispo.setVisible(true);

		frmBoutique.setVisible(true);
		
	}
}
