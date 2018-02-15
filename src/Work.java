import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Work extends JFrame {
	
	
	
	JLabel JL_fname,JL_sex,JL_age,JL_id,JL_birthday,JL_tel,JL_address,JL_select;
	 JTextField JT_fname,JT_sex,JT_age,JT_id,JT_birthday,JT_tel,JT_address;
	 JButton btn_insert,btn_update,btn_delete,btn_select;
	
	 
	 public Work(){
	     super("CRUD : ");
	     JL_id = new JLabel("Id_personne:");
	     JL_fname = new JLabel("nom:");
	     JL_sex = new JLabel("sexe:");
	     JL_age = new JLabel("age");
	     JL_birthday = new JLabel("date de naissance");
	     JL_tel = new JLabel("telephone");
	     JL_address = new JLabel("adresse");
	     
	     JL_id.setBounds(20, 20, 100, 20);
	     JL_fname.setBounds(20, 50, 100, 20);
	     JL_sex.setBounds(20, 80, 100, 20);
	     JL_age.setBounds(20, 110, 100, 20);
	     JL_birthday.setBounds(20, 140, 100, 20);
	     JL_tel.setBounds(20, 170, 100, 20);
	     JL_address.setBounds(20, 200, 100, 20);

	     
	     JT_id = new JTextField(20);
	     JT_fname = new JTextField(20);
	     JT_sex = new JTextField(20);
	     JT_age = new JTextField(20);
	     JT_birthday = new JTextField(20);
	     JT_tel = new JTextField(20);
	     JT_address = new JTextField(20);
	     
	     
	     JT_id.setBounds(130,20,150,20);
	     JT_fname.setBounds(130, 50, 150, 20);
	     JT_sex.setBounds(130, 80, 150, 20);
	     JT_age.setBounds(130, 110, 150, 20);
	     JT_birthday.setBounds(130, 140, 150, 20);
	     JT_tel.setBounds(130, 170, 150, 20);
	     JT_address.setBounds(130, 200, 150, 20);
	     
	     
	     
	     btn_insert = new JButton("Insert");
	     btn_update = new JButton("Update");
	     btn_delete = new JButton("Delete");
	     btn_select = new JButton("Select * from personne");
	     btn_insert.setBounds(300, 50, 80, 20);
	     btn_update.setBounds(300, 80, 80, 20);
	     btn_delete.setBounds(300, 110, 80, 20);
	     btn_select.setBounds(300, 300, 200, 20);
	     
	     
	     setLayout(null);
	     add(JL_id);
	     add(JL_fname);
	     add(JL_sex);
	     add(JL_age);
	     add(JL_birthday);
	     add(JL_tel);
	     add(JL_address);
	     add(JT_id);
	     add(JT_fname);
	     add(JT_sex);
	     add(JT_age);
	     add(JT_birthday);
	     add(JT_tel);
	     add(JT_address);
	     add(btn_insert);
	     add(btn_update);
	     add(btn_delete);
	     add(btn_select);
	     
	     


	   //button insert
	    btn_insert.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	             theQuery("insert into personne (ID_PERSONNE, NOM_PERSONNE,SEXE,AGE,DATE_DE_NAISSANCE,TEL_PERSONNE,ADRESSE) values("+JT_id.getText()+",'"+JT_fname.getText()+"','"+JT_sex.getText()+"',"+JT_age.getText()+",'"+JT_birthday.getText()+"','"+JT_tel.getText()+"','"+JT_address.getText()+"')");
	         }
	         catch(Exception ex){}
	         }
	     });
	    
	        //button update
	        btn_update.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	         
	           theQuery("update personne set nom_personne = '"+JT_fname.getText()+"',sexe = '"+JT_sex.getText()+"', age = "+JT_age.getText()+", DATE_DE_NAISSANCE  = '"+JT_birthday.getText()+"', TEL_PERSONNE = '"+JT_tel.getText()+"', ADRESSE = '"+JT_address.getText()+"' where ID_PERSONNE = "+JT_id.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	       
	         //button delete
	        btn_delete.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	          
	             theQuery("delete from personne where ID_PERSONNE = "+JT_id.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	        
	        btn_select.addActionListener(new  ActionListener() {
	        	 
		         public void actionPerformed(ActionEvent e) {
		         try{
		    
	        Connection connection = null;
	    	
	    	try {
	    	
	    	connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");	
	    	Statement stmt = null;
	    	stmt = connection.createStatement();
	    	ResultSet rs = stmt.executeQuery("Select * from personne");
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
				
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");	
			Statement stmt = null;
			stmt = connection.createStatement();
		    stmt.executeUpdate(query);
		    JOptionPane.showMessageDialog(null,"Query Executed");
		    
		      }catch(Exception ex){
		          JOptionPane.showMessageDialog(null,ex.getMessage());
		      }
		  }
		 
	 
}
