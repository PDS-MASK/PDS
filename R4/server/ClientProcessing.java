package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import parcourstype_view.Home_view;
import parcourstype_view.Information_customer;
import parcourstype_view.Personne;

public class ClientProcessing implements Runnable{

	/* Variable declaration*/
	private Socket clientConnected ; // The client we are treating
	private PrintWriter out ; // to send message to the client 
	private BufferedReader in ; // to receive message from the client 
	private String messageReceived; // stock the message from the client 
	private String respond; // stock the message to send to the client 
	private static volatile int id_cons;
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
		try {
			while (!clientConnected.isClosed()) {
				messageReceived = in.readLine();
				System.out.println(messageReceived);

				if(messageReceived.equals(null)) clientConnected.close();

				switch (messageReceived) {
				// the server is calling the bd with a select 
				case "select":
					System.out.println(Home_view.getSelected_id_consumer());
					respond = Personne.showSelectedIdCons(Home_view.getSelected_id_consumer(), Information_customer.getName_Selected_Cons());
					break;
				default :
					System.out.println(messageReceived);
					break;
				}
				// We send the result of the select to the client
				out.println(respond);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}