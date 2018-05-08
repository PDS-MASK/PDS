package indicateur;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

public class Boutiques_Dispo extends JFrame {
    public Boutiques_Dispo() {
        super();
        
        setTitle("Emplacements disponibles");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 
        
        String[] entetes = {"Prénom", "Nom"};
        Analyse a = new Analyse();
		List<Emplacement> l = new ArrayList<>();
        JTable tableau = new JTable();
        try {
			l = a.BoutiquesDispo();
			Object[][] data = new Object [l.size()][2];
			for(int i=0; i<l.size();i++) {
				System.out.println(i+"::::::"+l.get(i).getNom());
				data[i][0]=l.get(i).getNom();
				System.out.println("blaaaaaaaaaa"+"       "+data[i][0]);
				data[i][1]=l.get(i).getSurface();
				System.out.println("blaaaaaaaaaa"+"       "+data[i][1]);
				}
			System.out.println("bibibibibibi"+"       "+data[0][0]);
			System.out.println("bobobobobobo"+"       "+data[1][0]);
			tableau=new JTable(data,entetes);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
        getContentPane().add(tableau, BorderLayout.CENTER);
 
        pack();
    }
 
    public static void main(String[] args) {
        new Boutiques_Dispo().setVisible(true);
    }
}