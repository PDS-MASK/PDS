package Gestion_des_stocks;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import general.Administration;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gestion_des_stocks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNom;
	private JTextField txtCatgorie;
	private JTextField txtGenre;
	private JTextField txtDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_des_stocks frame1 = new Gestion_des_stocks();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void theQuery(String query){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","");	
			Statement stmt = null;
			stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(query);
			ResultSetMetaData resultMeta = result.getMetaData();

			while(result.next())
			{          
				for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
					System.out.print(result.getObject(i).toString());  
			}
			
			result.close();
			stmt.close();
			JOptionPane.showMessageDialog(null,"Query Executed");

			/*
			 *  String  sql = "SELECT adresse FROM customer WHERE login = " + login;
    //System.out.println(sql);

    Statement state = _conn.createStatement();
    ResultSet result = state.executeQuery(sql);
    ResultSetMetaData resultMeta = result.getMetaData();

    while(result.next())
    {          
        for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            System.out.print(result.getObject(i).toString());  
    }
    result.close();
        state.close();
			 */
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}
	

	/**
	 * Create the frame.
	 */
	public Gestion_des_stocks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblGestionDesStocks = new JLabel("Gestion des stocks");
		lblGestionDesStocks.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
		lblGestionDesStocks.setBounds(357, 5, 228, 41);
		contentPane.add(lblGestionDesStocks);

		JButton btnGestionDesBons = new JButton("Test affichage des produits");
		btnGestionDesBons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theQuery("SELECT * FROM ARTICLE");
			}
		});
		btnGestionDesBons.setBounds(239, 426, 315, 21);
		contentPane.add(btnGestionDesBons);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Administration a = new Administration();
				a.setVisible(true);
			}
		});
		btnRetour.setBounds(0, 10, 85, 21);
		contentPane.add(btnRetour);

		JButton btnAjouterUnArticle = new JButton("Ajouter un article");
		btnAjouterUnArticle.setBounds(10, 390, 201, 21);
		contentPane.add(btnAjouterUnArticle);

		JButton btnSupprimerUnArticle = new JButton("Supprimer un article");
		btnSupprimerUnArticle.setBounds(10, 436, 201, 21);
		contentPane.add(btnSupprimerUnArticle);

		table = new JTable();
		table.setBounds(24, 157, 385, -106);
		contentPane.add(table);

//		// create a table model and set a Column Identifiers to this model 
//		Object[] columns = {"Nom","Catégorie","Genre","Description"};
//		DefaultTableModel model = new DefaultTableModel();
//		model.setColumnIdentifiers(columns);
//
//		// set the model to the table
//		table.setModel(model);

		// Change A JTable Background Color, Font Size, Font Color, Row Height
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("",1,22);
		table.setFont(font);
		table.setRowHeight(30);

		// create JScrollPane
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(147, 56, 863, 324);

		getContentPane().setLayout(null);

		getContentPane().add(pane);
		
		JTextArea textNom = new JTextArea();
		textNom.setBounds(10, 88, 127, 22);
		contentPane.add(textNom);
		
		JTextArea textCategorie = new JTextArea();
		textCategorie.setBounds(10, 152, 127, 22);
		contentPane.add(textCategorie);
		
		JTextArea textGenre = new JTextArea();
		textGenre.setBounds(10, 225, 127, 22);
		contentPane.add(textGenre);
		
		JTextArea textDescription = new JTextArea();
		textDescription.setBounds(10, 297, 127, 22);
		contentPane.add(textDescription);
		
		txtNom = new JTextField();
		txtNom.setText("Nom");
		txtNom.setBounds(10, 67, 127, 19);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtCatgorie = new JTextField();
		txtCatgorie.setText("Cat\u00E9gorie");
		txtCatgorie.setColumns(10);
		txtCatgorie.setBounds(10, 123, 127, 19);
		contentPane.add(txtCatgorie);
		
		txtGenre = new JTextField();
		txtGenre.setText("Genre");
		txtGenre.setColumns(10);
		txtGenre.setBounds(10, 196, 127, 19);
		contentPane.add(txtGenre);
		
		txtDescription = new JTextField();
		txtDescription.setText("Description");
		txtDescription.setColumns(10);
		txtDescription.setBounds(10, 268, 127, 19);
		contentPane.add(txtDescription);
		
//		JButton btnModifierUnArticle = new JButton("Modifier un article");
//		btnModifierUnArticle.setBounds(10, 413, 201, 21);
//		contentPane.add(btnModifierUnArticle);
		
		 // create an array of objects to set the row data
        Object[] row = new Object[4];
        
        // button add row
        btnAjouterUnArticle.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = textNom.getText();
                row[1] = textCategorie.getText();
                row[2] = textGenre.getText();
                row[3] = textDescription.getText();
                
                // add row to the model
               // model.addRow(row);
            }
        });

     // button remove row
        btnSupprimerUnArticle.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    //model.removeRow(i);
                	theQuery("DELETE * FROM ARTICLE WHERE ID_ARTICLE="+table.getSelectedColumn());

                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
        
//     // button update row
//        btnModifierUnArticle.addActionListener(new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//             
//                // i = the index of the selected row
//                int i = table.getSelectedRow();
//                
//                if(i >= 0) 
//                {
//                   table.getModel().setValueAt(textNom.getText(), i, 0);
//                   table.getModel().setValueAt(textCategorie.getText(), i, 1);
//                   table.getModel().setValueAt(textGenre.getText(), i, 2);
//                   table.getModel().setValueAt(textDescription.getText(), i, 3);
//                }
//                else{
//                    System.out.println("Update Error");
//                }
//            }
//        });
        
        showTableData();

	}
	
	
  	public void showTableData(){
		try{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ahamdi","");
		String sql = "Select * from article";
		Object pst = connection.prepareStatement(sql);
		Object rs = ((PreparedStatement) pst).executeQuery();
		table.setModel(DbUtils.resultSetToTableModel((ResultSet) rs));
		System.out.print(" "+ rs);
		
		}
		catch(Exception ex){
		JOptionPane.showMessageDialog(null, ex);
		 
		}
		 
		}
}
