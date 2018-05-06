package indicateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.security.Permissions;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Client extends JFrame {

	private JFrame frmIndicateurs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
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
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIndicateurs = new JFrame();
		frmIndicateurs.setTitle("Clients");
		frmIndicateurs.setBounds(100, 100, 450, 300);
		frmIndicateurs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIndicateurs.getContentPane().setLayout(null);
		
		JButton btnAujourdhui = new JButton("Aujourd'hui");
		btnAujourdhui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analyse a = new Analyse();
				try {
					a.afficheP();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAujourdhui.setBounds(44, 122, 89, 23);
		frmIndicateurs.getContentPane().add(btnAujourdhui);
		
		JLabel lblListeDesClients = new JLabel("Liste des clients ayant visit\u00E9 le centre commercial");
		lblListeDesClients.setFont(new Font("Calibri", Font.BOLD, 16));
		lblListeDesClients.setBounds(36, 36, 344, 59);
		frmIndicateurs.getContentPane().add(lblListeDesClients);
	}
}
