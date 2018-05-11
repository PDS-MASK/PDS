package general;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(104, 118, 68, 29);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(104, 149, 68, 29);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(182, 123, 96, 19);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(182, 154, 96, 19);
		frame.getContentPane().add(password);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname = username.getText();
				String psd = password.getText();
				
				if (uname.equals("root") && (psd.equals("password"))) {
					JOptionPane.showMessageDialog(frame, "connexion réalisée avec succès");
					frame.dispose();
					Administration a;
					try {
						a = new Administration();
						a.setVisible(true);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "connexion échouée (login ou mot de passe incorrect)");
				}
				
				
			}
		});
		btnConnexion.setBounds(182, 191, 96, 21);
		frame.getContentPane().add(btnConnexion);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Mask_Logo_2.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(10, 10, 405, 98);
		frame.getContentPane().add(lblNewLabel);
	}
	
	
	
}