

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Work extends JFrame {
	
	JLabel JL_valeurEmplacement,JL_disponibilite,JL_superficie,JL_tauxDeFrequentation,JL_centre,JL_magasin,JL_idemplacement;
	 JTextField JT_valeurEmplacement,JT_disponibilite,JT_superficie,JT_tauxDeFrequentation,JT_centre,JT_magasin,JT_idemplacement;
	 JButton btn_insert,btn_update,btn_delete,btn_select;

	 
	 public Work(){
	     super("TPPPPP : ");
	     JL_valeurEmplacement = new JLabel("valeurEmplacement:");
	     JL_disponibilite = new JLabel("disponibilite:");
	     JL_superficie = new JLabel("superficie:");
	     JL_tauxDeFrequentation = new JLabel("tauxDeFrequentation");
	     JL_centre = new JLabel("centre");
	     JL_magasin = new JLabel("magasin");
	     JL_idemplacement = new JLabel("idEmplacement");
	     
	     JL_valeurEmplacement.setBounds(20, 170, 100, 20);
	     JL_disponibilite.setBounds(20, 50, 100, 20);
	     JL_superficie.setBounds(20, 80, 100, 20);
	     JL_tauxDeFrequentation.setBounds(20, 110, 100, 20);
	     JL_centre.setBounds(20, 130, 100, 20);
	     JL_magasin.setBounds(20, 150, 100, 20);
	     JL_idemplacement.setBounds(20, 20, 100, 20);
	     
	     JT_valeurEmplacement = new JTextField(20);
	     JT_disponibilite = new JTextField(20);
	     JT_superficie = new JTextField(20);
	     JT_tauxDeFrequentation = new JTextField(20);
	     JT_centre = new JTextField(20);
	     JT_magasin = new JTextField(20);
	     JT_idemplacement = new JTextField(20);
	     
	     JT_valeurEmplacement.setBounds(130,170,150,20);
	     JT_disponibilite.setBounds(130, 50, 150, 20);
	     JT_superficie.setBounds(130, 80, 150, 20);
	     JT_tauxDeFrequentation.setBounds(130, 110, 150, 20);
	     JT_centre.setBounds(130, 130, 150, 20);
	     JT_magasin.setBounds(130, 150, 150, 20);
	     JT_idemplacement.setBounds(130,20,150,20);
	     
	     btn_insert = new JButton("Créer");
	     btn_update = new JButton("Update");
	     btn_delete = new JButton("Delete");
	     btn_select = new JButton("Afficher la liste des emplacements");
	     btn_insert.setBounds(300, 50, 80, 20);
	     btn_update.setBounds(300, 80, 80, 20);
	     btn_delete.setBounds(300, 110, 80, 20);
	     btn_select.setBounds(300, 300, 200, 20);
	     
	     setLayout(null);
	     add(JL_valeurEmplacement);
	     add(JL_disponibilite);
	     add(JL_superficie);
	     add(JL_tauxDeFrequentation);
	     add(JL_centre);
	     add(JL_magasin);
	     add(JL_idemplacement);
	     add(JT_valeurEmplacement);
	     add(JT_disponibilite);
	     add(JT_superficie);
	     add(JT_tauxDeFrequentation);
	     add(JT_centre);
	     add(JT_magasin);
	     add(JT_idemplacement);
	     add(btn_insert);
	     add(btn_update);
	     add(btn_delete);
	     add(btn_select);

	   //button insert un emplacement
	    btn_insert.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	        	 theQuery("insert into emplacement (IDEMPLACEMENT, VALEUREMPLACEMENT,DISPONIBILITE,SUPERFICIE,TAUXFREQUENTATION,FK_IDCENTRECOMMERCIAL,FK_IDMAGASIN) values("+JT_idemplacement.getText()+",'"+JT_valeurEmplacement.getText()+"','"+JT_disponibilite.getText()+"',"+JT_superficie.getText()+",'"+JT_tauxDeFrequentation.getText()+"','"+JT_centre.getText()+"','"+JT_magasin.getText()+"')");
	         }
	         catch(Exception ex){}
	         }
	     });
	    
	        //button update
	        btn_update.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	         
	           //theQuery("ext()+"',sexe = '"+JT_lname.getText()+"', age = "+JT_age.getText()+", DATE_DE_NAISSANCE  = '"+JT_birthday.getText()+"', TEL_PERSONNE = '"+JT_tel.getText()+"', ADRESSE = '"+JT_address.getText()+"' where ID_PERSONNE = "+JT_id.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	       
	         //button delete
	        btn_delete.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	          
	             theQuery("delete from emplacement where IDEMPLACEMENT = "+JT_idemplacement.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	        
	        btn_select.addActionListener(new  ActionListener() {
	        	 
		         public void actionPerformed(ActionEvent e) {
		         try{
		    
	        Connection connection = null;
	    	
	    	try {
	    	
	    	connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","password");	
	    	Statement stmt = null;
	    	stmt = connection.createStatement();
	    	ResultSet rs = stmt.executeQuery("Select * from EMPLACEMENT");
	    	ResultSetMetaData rsmd = rs.getMetaData();
	    	
	    	int rowsSubmit = 0;
	    	String resultat = " ";
	    	int columnsNumber = rsmd.getColumnCount();
	    	while (rs.next()) {
	    		
	    		rowsSubmit++;
	    		
	    		for (int i = 1; i<= columnsNumber; i++)
	    			
	    		{
	    			resultat = resultat +" "+ rs.getString(i);
	    			System.out.print(" "+ rs.getString(i));
	    			
	    		}
	    		System.out.println();
	    	}
	    	
	    	JOptionPane.showMessageDialog(null,resultat);
    		
	    	} catch (SQLException ed) {
	    		
	    		System.out.println("Connection Failed! Check output console");
	    		ed.printStackTrace();
	    		return;
	    	}
		         }
		         catch(Exception ex){}
		         }
		         
		         
		     });
	     
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setVisible(true);
	     setLocationRelativeTo(null);
	     setSize(800,600);

}
		public void theQuery(String query){
			Connection connection = null;
			
			try {
				
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","password");	
			Statement stmt = null;
			stmt = connection.createStatement();
		    stmt.executeUpdate(query);
		    JOptionPane.showMessageDialog(null,"Query Executed");
		    
		      }catch(Exception ex){
		          JOptionPane.showMessageDialog(null,ex.getMessage());
		      }
		  }
		 
	 
}
