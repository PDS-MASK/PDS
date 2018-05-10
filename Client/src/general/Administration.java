package general;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Administration extends JFrame {

	private JPanel contentPane;
	public static Socket sock;

	/**
	 * Create the frame.
	 */
	public Administration() throws UnknownHostException, IOException {
		if(sock == null) {
			sock = new Socket(InetAddress.getLocalHost(),9003);
		}
		else if(sock.isClosed()) {
			sock = new Socket(InetAddress.getLocalHost(),9003);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSON.GenerateurJSON.Ecriture_JSON("RETOUR","PROFIL","");
				try {
					JSON.Client.Envoyer_Json();
					System.out.println("FERME");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				setVisible(false);
				System.exit(0);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);
		
		JButton btnGestionDesStocks = new JButton("Gestion des stocks");
		btnGestionDesStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion_des_stocks.Gestion_des_stocks g = new Gestion_des_stocks.Gestion_des_stocks();
				g.setVisible(true);
			}
		});
		btnGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesStocks.setBounds(89, 79, 256, 21);
		contentPane.add(btnGestionDesStocks);
		
		JButton btnGestionDesProfils = new JButton("Profil des clients");
		btnGestionDesProfils.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				profil.WindowsBuilder g = new profil.WindowsBuilder();
				g.setVisible(true);
			}
		});
		btnGestionDesProfils.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesProfils.setBounds(89, 220, 256, 21);
		contentPane.add(btnGestionDesProfils);
		
		JButton btnGestionDesEmplacements = new JButton("Gestion des emplacements");
		btnGestionDesEmplacements.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnGestionDesEmplacements.setBounds(89, 110, 256, 21);
		contentPane.add(btnGestionDesEmplacements);
		
		JButton btnAnalyserLesIndicateurs = new JButton("Analyser les indicateurs");
		btnAnalyserLesIndicateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				indicateur.Indicateur g = new indicateur.Indicateur();
				g.setVisible(true);
			}
		});
		btnAnalyserLesIndicateurs.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
		btnAnalyserLesIndicateurs.setBounds(89, 141, 256, 21);
		contentPane.add(btnAnalyserLesIndicateurs);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
		lblAdministration.setBounds(135, 28, 182, 21);
		contentPane.add(lblAdministration);
	}
	
	

}