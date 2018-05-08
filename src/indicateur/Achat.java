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
	private JTextField textField;

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
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(38, 56, 113, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIdBoutique = new JLabel("ID Boutique");
		lblIdBoutique.setBounds(38, 28, 86, 14);
		getContentPane().add(lblIdBoutique);

		JLabel jour = new JLabel("");
		JButton btnLaJourne = new JButton("La journ\u00E9e");
		btnLaJourne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Analyse a = new Analyse();
				float nb ;
				try {
					nb = a.achatsjour();
					jour.setText(Float.toString(nb));

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLaJourne.setBounds(38, 120, 113, 23);
		getContentPane().add(btnLaJourne);
		
		JLabel lblSommeDesAchats = new JLabel("Somme des achats");
		lblSommeDesAchats.setBounds(38, 95, 113, 14);
		getContentPane().add(lblSommeDesAchats);
		
		jour.setBounds(250, 120, 46, 23);
		getContentPane().add(jour);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
