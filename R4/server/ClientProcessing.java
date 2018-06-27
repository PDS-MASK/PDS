package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class ClientProcessing implements Runnable{

	/* Variable declaration*/
	private Socket clientConnected ; // The client we are treating
	private PrintWriter out ; // to send message to the client 
	private BufferedReader in ; // to receive message from the client 
	private String messageReceived; // stock the message from the client 
	private String respond; // stock the message to send to the client 
	private static volatile int id_cons;
	public static PoolConnexion pool = new PoolConnexion();
	static Connection con = connect();
	/* Getter & Setter */

	public static int getId_cons() {
		return id_cons;
	}

	public static void setId_cons(int id_cons) {
		ClientProcessing.id_cons = id_cons;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public String getMessageReceived() {
		return messageReceived;
	}

	public static Connection connect() {
		try {
			con = pool.getConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erreur de connection a la base de donnees :" + e);
		}
		/*retourne un object con de type Connection*/
		return con;
	}
	/* Constructor */
	public ClientProcessing(Socket clientConnected) {
		try {
			this.clientConnected = clientConnected;
			setOut(new PrintWriter(new OutputStreamWriter(clientConnected.getOutputStream()),true)) ; 
			setIn(new BufferedReader(new InputStreamReader(clientConnected.getInputStream()))) ;
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/* Methods */

	/**
	 * This method describe "how to treat a client request" 
	 * While the client is connected 
	 * 1 : We take his demand
	 * 2 : based on what he sent we are able to perform differents actions
	 * 3 : We sent it back the result of his request 
	 */
	public void run() {
		String boutiqueSelectionnee="";
		try {
			while (!clientConnected.isClosed()) {
				messageReceived = in.readLine();
				System.out.println(messageReceived);

				if(messageReceived.equals(null)) clientConnected.close();

				if(messageReceived.contains("select_gestion_stock")) {
					boutiqueSelectionnee = messageReceived.substring(20);
					System.out.println("Boutique reçu : " + boutiqueSelectionnee);
					messageReceived = "select_gestion_stock";
				}

				switch (messageReceived) {
				// the server is calling the bd with a select 
				case "select_gestion_stock":
					System.out.println("Case select_gestion_stock");
					System.out.println("BOUTIQUE : " +boutiqueSelectionnee);
					Statement sta2 = con.createStatement();
					String Sql2 = "select * from boutique where boutique.nom_boutique ='" + boutiqueSelectionnee + "'";
					ResultSet rs2 = sta2.executeQuery(Sql2);
					rs2.next();
					String i = rs2.getString("id_boutique");
					respond = i;
					System.out.println(respond);
					out.println(respond);
					out.flush();
					
					break;

				default :
					System.out.println(messageReceived);
					break;
				}
				// We send the result of the select to the client
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}