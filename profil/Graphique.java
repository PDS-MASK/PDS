package profil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;

public class Graphique extends JFrame implements ActionListener{
	private JFrame windows;
	private JButton profil = new JButton("Profil");
	private JButton update = new JButton("Update");
	private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Le JLabel");
	  
	public Graphique()  {
		this.setTitle("Animation");
		  this.setSize(300, 300);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setLocationRelativeTo(null);
		  container.setBackground(Color.white);
		  container.setLayout(new BorderLayout());
		  JPanel south = new JPanel();
		  south.add(profil);
		  south.add(update);
		  container.add(south, BorderLayout.SOUTH);
		  container.add(label, BorderLayout.NORTH);
		  update.addActionListener(this);
		  profil.addActionListener(this);
		  this.setContentPane(container);

		  this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		  if(arg0.getSource() == update)
		    label.setText("Vous avez cliqué sur le bouton 1");
		  	Profil p;
			try {
				p = new Profil(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  if(arg0.getSource() == profil)
		    label.setText("Vous avez cliqué sur le bouton 2");
		}
	
	public static void main(String[] args) {
		Graphique g = new Graphique();
	}

}
