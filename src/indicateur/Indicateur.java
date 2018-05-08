package indicateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Indicateur extends JFrame{

	private JFrame frmIndicateurs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Indicateur window = new Indicateur();
					window.frmIndicateurs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Indicateur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIndicateurs = new JFrame();
		frmIndicateurs.setTitle("Indicateurs");
		frmIndicateurs.setBounds(100, 100, 450, 300);
		frmIndicateurs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIndicateurs.getContentPane().setLayout(null);
		
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIndicateurs.setVisible(false);
				Client c = new Client();
				//c.setVisible(true);
				System.out.println("bouton client");
			}
		});
		btnClients.setBounds(183, 151, 91, 33);
		frmIndicateurs.getContentPane().add(btnClients);
		
		JButton btnBoutiques = new JButton("Boutiques");
		btnBoutiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmIndicateurs.setVisible(false);
				Boutiques b = new Boutiques();
				//b.setVisible(true);
			}
		});
		btnBoutiques.setBounds(183, 107, 91, 33);
		frmIndicateurs.getContentPane().add(btnBoutiques);
		
		JLabel lblNewLabel = new JLabel("Affichage des indicateurs");
		lblNewLabel.setBounds(159, 47, 300, 14);
		frmIndicateurs.getContentPane().add(lblNewLabel);
		frmIndicateurs.setVisible(true);
	}
}
