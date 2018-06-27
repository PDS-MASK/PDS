package parcourstype_view;

package general;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Administration extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public Administration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionDesStocks = new JButton("Gestion des stocks");
		btnGestionDesStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_des_stocks.Home g;
				setVisible(false);
				try {
					g = new Gestion_des_stocks.Home();
					g.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesStocks.setBounds(89, 79, 256, 21);
		contentPane.add(btnGestionDesStocks);
		
		
		JButton btnParcours = new JButton("Parcours type");
		btnGestionDesStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try {
					Home_view window = new Home_view();
					window.frmPlateformeDesParcours.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesStocks.setBounds(89, 79, 256, 21);
		contentPane.add(btnGestionDesStocks);
		
		JButton btnGestionDesEmplacements = new JButton("Gestion des emplacements");
		btnGestionDesEmplacements.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesEmplacements.setBounds(89, 110, 256, 21);
		contentPane.add(btnGestionDesEmplacements);
		
		JButton btnAnalyserLesIndicateurs = new JButton("Analyser les indicateurs");
		btnAnalyserLesIndicateurs.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnAnalyserLesIndicateurs.setBounds(89, 141, 256, 21);
		contentPane.add(btnAnalyserLesIndicateurs);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
		lblAdministration.setBounds(135, 28, 182, 21);
		contentPane.add(lblAdministration);
		

	}
	
	

}