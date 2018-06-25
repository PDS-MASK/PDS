package parcourstype_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import net.proteanit.sql.DbUtils;
import server.ClientProcessing;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Information_customer {

	private JFrame frmPlateformeDesParcours;
	private JTextField txtLeConsommateurQui;
	private static JTextField Name_Selected_Cons;
	private static int id_consumer;
	private JTextField txtCettePersonnePossde;
	private JTextField SelectedProfilCons;
	private JTextField txtVosHabitudesDachats;
	private JTextField SelectedProfilPurchasePreference;

	public Information_customer(int atomicInteger)
	{

		this.id_consumer = atomicInteger;

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Information_customer window = new Information_customer();
					window.frmPlateformeDesParcours.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Information_customer() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	void initialize() throws IOException {
		frmPlateformeDesParcours = new JFrame();
		frmPlateformeDesParcours.setTitle("Plateforme des parcours type");
		frmPlateformeDesParcours.setBounds(100, 100, 741, 633);
		frmPlateformeDesParcours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlateformeDesParcours.getContentPane().setLayout(null);

		txtLeConsommateurQui = new JTextField();
		txtLeConsommateurQui.setEditable(false);
		txtLeConsommateurQui.setBackground(SystemColor.menu);
		txtLeConsommateurQui.setText("Le consommateur qui a \u00E9t\u00E9 selectionn\u00E9 est :");
		txtLeConsommateurQui.setBounds(12, 76, 263, 22);
		frmPlateformeDesParcours.getContentPane().add(txtLeConsommateurQui);
		txtLeConsommateurQui.setColumns(10);

		Name_Selected_Cons = new JTextField();
		Name_Selected_Cons.setEditable(false);
		Name_Selected_Cons.setBackground(SystemColor.menu);
		Name_Selected_Cons.setBounds(287, 76, 200, 22);
		frmPlateformeDesParcours.getContentPane().add(Name_Selected_Cons);
		Name_Selected_Cons.setColumns(10);

		txtCettePersonnePossde = new JTextField();
		txtCettePersonnePossde.setText("Cette personne poss\u00E8de le(s) profil(s) suivant(s) :");
		txtCettePersonnePossde.setEditable(false);
		txtCettePersonnePossde.setColumns(10);
		txtCettePersonnePossde.setBackground(SystemColor.menu);
		txtCettePersonnePossde.setBounds(12, 142, 294, 22);
		frmPlateformeDesParcours.getContentPane().add(txtCettePersonnePossde);

		SelectedProfilCons = new JTextField();
		SelectedProfilCons.setEditable(false);
		SelectedProfilCons.setColumns(10);
		SelectedProfilCons.setBackground(SystemColor.menu);
		SelectedProfilCons.setBounds(318, 142, 211, 68);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilCons);

		txtVosHabitudesDachats = new JTextField();
		txtVosHabitudesDachats.setText("Vos habitudes d'achats sont : ");
		txtVosHabitudesDachats.setEditable(false);
		txtVosHabitudesDachats.setColumns(10);
		txtVosHabitudesDachats.setBackground(SystemColor.menu);
		txtVosHabitudesDachats.setBounds(12, 341, 180, 22);
		frmPlateformeDesParcours.getContentPane().add(txtVosHabitudesDachats);

		SelectedProfilPurchasePreference = new JTextField();
		SelectedProfilPurchasePreference.setEditable(false);
		SelectedProfilPurchasePreference.setColumns(10);
		SelectedProfilPurchasePreference.setBackground(SystemColor.menu);
		SelectedProfilPurchasePreference.setBounds(206, 326, 319, 52);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilPurchasePreference);

		JButton btnNewButton = new JButton("Proposez moi un parcours !");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPlateformeDesParcours.setVisible(false);
				itinerary_view iv = new itinerary_view(SelectedProfilPurchasePreference.getText());
				iv.initialize();

			}
		});
		btnNewButton.setBounds(228, 500, 250, 32);
		frmPlateformeDesParcours.getContentPane().add(btnNewButton);
		frmPlateformeDesParcours.setVisible(true);

		Sous_Categorie sc = new Sous_Categorie();
		sc.showSelectedConsPurchasePreference(this.getId_consumer(), SelectedProfilPurchasePreference);
		java.util.Iterator<String> itr = sc.preference.iterator();
		
		
		Personne p = new Personne();
		JOptionPane.showMessageDialog(null,Home_view.getSelected_id_consumer());
		
		Home_view.getOut().println("select");
		String reponse = Home_view.getIn().readLine();
		System.out.println(reponse);
		Information_customer.getName_Selected_Cons().setText(reponse);
		
		
		p.showSelectedIdCons(this.getId_consumer(),Name_Selected_Cons);
		p.showSelectedConsProfil(this.getId_consumer(),SelectedProfilCons);


		while(itr.hasNext())

		{
			System.out.println(itr.next());

		}


	}



	public JFrame getFrame() {
		return frmPlateformeDesParcours;
	}

	public void setFrame(JFrame frame) {
		this.frmPlateformeDesParcours = frame;
	}

	public JTextField getTxtLeConsommateurQui() {
		return txtLeConsommateurQui;
	}

	public void setTxtLeConsommateurQui(JTextField txtLeConsommateurQui) {
		this.txtLeConsommateurQui = txtLeConsommateurQui;
	}

	public static JTextField getName_Selected_Cons() {
		return Name_Selected_Cons;
	}

	public void setName_Selected_Cons(JTextField name_Selected_Cons) {
		Name_Selected_Cons = name_Selected_Cons;
	}

	public static int getId_consumer() {
		return id_consumer;
	}

	public void setId_consumer(int id_consumer) {
		this.id_consumer = id_consumer;
	}
}
