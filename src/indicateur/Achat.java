package indicateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Achat extends JFrame{

	private JFrame frame;
	private JTextField id_boutique;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Achat window = new Achat();
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
	public Achat() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Achats");
		frame.getContentPane().setLayout(null);
		JButton retour = new JButton("retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Indicateur c = new Indicateur();
				//c.setVisible(true);
			}
		});
		retour.setBounds(350, 230, 75, 20);
		frame.add(retour);
		id_boutique = new JTextField();
		id_boutique.setBounds(38, 56, 113, 20);
		frame.getContentPane().add(id_boutique);
		id_boutique.setColumns(10);
		
		JLabel lblIdBoutique = new JLabel("ID Boutique");
		lblIdBoutique.setBounds(38, 28, 86, 14);
		frame.getContentPane().add(lblIdBoutique);

		JLabel jour = new JLabel("");
		JButton btnLaJourne = new JButton("La journ\u00E9e");
		btnLaJourne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analyse a = new Analyse();
				float nb ;
				try {
					String id= id_boutique.getText();
					nb = a.achatsjour(id);
					jour.setText(Float.toString(nb) + "€");

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLaJourne.setBounds(38, 134, 113, 23);
		frame.getContentPane().add(btnLaJourne);
		
		JLabel lblSommeDesAchats = new JLabel("Somme des achats");
		lblSommeDesAchats.setBounds(38, 100, 113, 23);
		frame.getContentPane().add(lblSommeDesAchats);
		
		jour.setBounds(250, 120, 46, 23);
		frame.getContentPane().add(jour);
		JLabel mois = new JLabel("");
		mois.setBounds(250, 176, 46, 23);
		JButton btnNewButton = new JButton("Le mois");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analyse a = new Analyse();
				float nb ;
				try {
					String id= id_boutique.getText();
					nb = a.achatsmois(id);
					mois.setText(Float.toString(nb) + "€");

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(35, 176, 116, 23);
		frame.getContentPane().add(btnNewButton);
		
	
		frame.getContentPane().add(mois);
		frame.setVisible(true);
		
		
	}
}
