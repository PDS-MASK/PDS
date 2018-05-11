package profil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import general.Administration;

import java.awt.Font;

public class WindowsBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputIdProfil;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowsBuilder frame = new WindowsBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WindowsBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Profil");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Administration a = new Administration();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);
		txtInputIdProfil = new JTextField();
		txtInputIdProfil.setText("");
		txtInputIdProfil.setBounds(128, 90, 86, 20);
		contentPane.add(txtInputIdProfil);
		txtInputIdProfil.setColumns(10);
		
		JButton btnShowThisProfil = new JButton("Show this Profil");
		btnShowThisProfil.setAction(action);
		btnShowThisProfil.setBounds(236, 70, 132, 23);
		contentPane.add(btnShowThisProfil);
		
		JButton btnUpdateThisProfil = new JButton("Update this Profil");
		btnUpdateThisProfil.setAction(action_1);
		btnUpdateThisProfil.setBounds(236, 104, 132, 23);
		contentPane.add(btnUpdateThisProfil);
		
		JButton btnShowAllProfils = new JButton("Show All Profils");
		btnShowAllProfils.setAction(action_2);
		btnShowAllProfils.setBounds(74, 168, 113, 23);
		contentPane.add(btnShowAllProfils);
		
		JButton btnUpdateAllProfil = new JButton("Update All Profil");
		btnUpdateAllProfil.setAction(action_3);
		btnUpdateAllProfil.setBounds(224, 168, 113, 23);
		contentPane.add(btnUpdateAllProfil);
		
		JLabel lblInputIdProfil = DefaultComponentFactory.getInstance().createTitle("Input Id Profil : ");
		lblInputIdProfil.setBounds(41, 93, 88, 14);
		contentPane.add(lblInputIdProfil);
		
		JLabel lblShowUpdate = DefaultComponentFactory.getInstance().createLabel("SHOW / UPDATE PROFIL");
		lblShowUpdate.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblShowUpdate.setBounds(107, 10, 276, 37);
		contentPane.add(lblShowUpdate);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Show One profil");
			putValue(SHORT_DESCRIPTION, "");
		}
		public void actionPerformed(ActionEvent e) {
			JSON.GenerateurJSON.Ecriture_JSON("SHOW_ONE_PROFIL","PROFIL",txtInputIdProfil.getText());
			try {
				JSON.Client.Envoyer_Json();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			int id_profil = 0;
			try {
			    id_profil = Integer.parseInt(txtInputIdProfil.getText());
				JFrame windows = new JFrame("Show All Profil");
				try {
					if(general.Administration.sock.isClosed()) {
						general.Administration.sock = new Socket(InetAddress.getLocalHost(),9003);

					}
					
					  JSON.Commun.transfert( general.Administration.sock.getInputStream(), new FileOutputStream("json_serveur"), true);
						general.Administration.sock.close();

				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				  Object[][] donnees = LecteurJSON.Get_Liste();
			        String[] entetes = {"ID", "Nom", "Prenom", "Profil", "Categorie"};

			        JTable tableau = new JTable( donnees, entetes);
			        windows.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
			        windows.pack();
					windows.setVisible(true);
					
					
			} catch (NumberFormatException e1){
				e1.printStackTrace();
			}
			

		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Update One Profil");
			putValue(SHORT_DESCRIPTION, "Update One Profil");
		}
		public void actionPerformed(ActionEvent e) {
			JSON.GenerateurJSON.Ecriture_JSON("UPDATE_ONE_PROFIL","PROFIL",txtInputIdProfil.getText());
			try {
				JSON.Client.Envoyer_Json();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		
		}
	}
	private class SwingAction_2 extends AbstractAction  {
		public SwingAction_2() {
			putValue(NAME, "Show All Profil");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e)   {
			JSON.GenerateurJSON.Ecriture_JSON("SHOW_ALL_PROFIL","PROFIL","");
			try {
				JSON.Client.Envoyer_Json();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			
			JFrame windows = new JFrame("Show All Profil");
			try {
				if(!general.Administration.sock.isClosed()) {
					general.Administration.sock.close();
				}
				if(general.Administration.sock.isClosed()) {
					general.Administration.sock = new Socket(InetAddress.getLocalHost(),9003);

				}
				  JSON.Commun.transfert( general.Administration.sock.getInputStream(), new FileOutputStream("json_serveur"), true);
				  general.Administration.sock.close();
			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			  Object[][] donnees = LecteurJSON.Get_Liste();
		        String[] entetes = {"ID", "Nom", "Prenom", "Profil", "Categorie"};

		        JTable tableau = new JTable( donnees, entetes);
		        windows.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		        windows.pack();
				windows.setVisible(true);
//				try {
//					if(general.Administration.sock.isClosed()) {
//						general.Administration.sock = new Socket(InetAddress.getLocalHost(),9003);
//
//					}
//				} catch (UnknownHostException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				} catch (IOException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}

				
//				 try {
//					general.Administration.sock.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} 


		}
		
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Update All Profil");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JSON.GenerateurJSON.Ecriture_JSON("UPDATE_ALL_PROFIL","PROFIL","");
			try {
				JSON.Client.Envoyer_Json();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


		}
	}
}
