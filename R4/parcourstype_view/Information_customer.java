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
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Information_customer {

	private JFrame frmPlateformeDesParcours;
	private static JTextField Name_Selected_Cons;
	private static int id_consumer;
	private static JTextField SelectedProfilCons;
	private  static JTextField SelectedProfilPurchasePreference;
	private JLabel lblNewLabel;
	private JLabel lblProfilsIdentifis;
	private JLabel lblHabitudesDachats;
	private JPanel panel;
	private JLabel lblPlateformeParcourstype;
	private JLabel lblNewLabel_1;

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
		frmPlateformeDesParcours.setBackground(SystemColor.inactiveCaptionBorder);
		frmPlateformeDesParcours.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmPlateformeDesParcours.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
		frmPlateformeDesParcours.setBounds(100, 100, 741, 633);
		frmPlateformeDesParcours.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPlateformeDesParcours.getContentPane().setLayout(null);

		Name_Selected_Cons = new JTextField();
		Name_Selected_Cons.setEditable(false);
		Name_Selected_Cons.setBackground(SystemColor.inactiveCaptionBorder);
		Name_Selected_Cons.setBounds(325, 107, 200, 22);
		frmPlateformeDesParcours.getContentPane().add(Name_Selected_Cons);
		Name_Selected_Cons.setColumns(10);

		SelectedProfilCons = new JTextField();
		SelectedProfilCons.setEditable(false);
		SelectedProfilCons.setColumns(10);
		SelectedProfilCons.setBackground(SystemColor.inactiveCaptionBorder);
		SelectedProfilCons.setBounds(328, 229, 211, 68);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilCons);

		SelectedProfilPurchasePreference = new JTextField();
		SelectedProfilPurchasePreference.setEditable(false);
		SelectedProfilPurchasePreference.setColumns(10);
		SelectedProfilPurchasePreference.setBackground(SystemColor.inactiveCaptionBorder);
		SelectedProfilPurchasePreference.setBounds(325, 407, 319, 52);
		frmPlateformeDesParcours.getContentPane().add(SelectedProfilPurchasePreference);

		JButton btnNewButton = new JButton("Proposez moi un parcours !");
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
		
		lblNewLabel = new JLabel("Personne selection\u00E9e :");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setBounds(328, 62, 160, 16);
		frmPlateformeDesParcours.getContentPane().add(lblNewLabel);
		
		lblProfilsIdentifis = new JLabel("Profil(s) identifi\u00E9(s) :");
		lblProfilsIdentifis.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblProfilsIdentifis.setBounds(325, 185, 163, 16);
		frmPlateformeDesParcours.getContentPane().add(lblProfilsIdentifis);
		
		lblHabitudesDachats = new JLabel("Habitude(s) d'achat(s) :");
		lblHabitudesDachats.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHabitudesDachats.setBackground(SystemColor.inactiveCaptionBorder);
		lblHabitudesDachats.setBounds(325, 378, 163, 16);
		frmPlateformeDesParcours.getContentPane().add(lblHabitudesDachats);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 287, 586);
		frmPlateformeDesParcours.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblPlateformeParcourstype = new JLabel("Plateforme parcours-type");
		lblPlateformeParcourstype.setBounds(44, 249, 231, 20);
		lblPlateformeParcourstype.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPlateformeParcourstype.setForeground(SystemColor.inactiveCaptionBorder);
		panel.add(lblPlateformeParcourstype);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\BEN MALEK Mohamed\\Downloads\\Mask_Logo_2.png"));
		lblNewLabel_1.setBounds(58, 45, 158, 73);
		panel.add(lblNewLabel_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPlateformeDesParcours.setVisible(false);
				itinerary_view iv = new itinerary_view(SelectedProfilPurchasePreference.getText());
				iv.initialize();

			}
		});
		btnNewButton.setBounds(325, 516, 250, 32);
		frmPlateformeDesParcours.getContentPane().add(btnNewButton);
		frmPlateformeDesParcours.setVisible(true);

		Sous_Categorie sc = new Sous_Categorie();
		sc.showSelectedConsPurchasePreference(this.getId_consumer(), SelectedProfilPurchasePreference);
		java.util.Iterator<String> itr = sc.preference.iterator();
		
		
		Personne p = new Personne();
		JOptionPane.showMessageDialog(null,Home_view.getSelected_id_consumer());
		
		Home_view.getOut().println("select name cons");
		String reponse_name = Home_view.getIn().readLine();
		System.out.println(reponse_name);
		Information_customer.getName_Selected_Cons().setText(reponse_name);
		
		Home_view.getOut().println("select profil cons");
		String reponse_profil = Home_view.getIn().readLine();
		System.out.println("heyyyyyyyyyyyyyy   "+reponse_profil);
		Information_customer.getSelectedProfilCons().setText(reponse_profil);
		
		
		p.showSelectedIdCons(this.getId_consumer(),Name_Selected_Cons);
		//p.showSelectedConsProfil(this.getId_consumer(),SelectedProfilCons);
		
		


		while(itr.hasNext())

		{
			System.out.println(itr.next());

		}


	}



	public static JTextField getSelectedProfilCons() {
		return SelectedProfilCons;
	}

	public void setSelectedProfilCons(JTextField selectedProfilCons) {
		SelectedProfilCons = selectedProfilCons;
	}

	public static JTextField getSelectedProfilPurchasePreference() {
		return SelectedProfilPurchasePreference;
	}

	public void setSelectedProfilPurchasePreference(JTextField selectedProfilPurchasePreference) {
		SelectedProfilPurchasePreference = selectedProfilPurchasePreference;
	}

	public JFrame getFrame() {
		return frmPlateformeDesParcours;
	}

	public void setFrame(JFrame frame) {
		this.frmPlateformeDesParcours = frame;
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
