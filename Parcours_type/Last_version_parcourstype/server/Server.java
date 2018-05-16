package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

	/* Variable declaration */

	private ServerSocket socketserver; // the server 
	private boolean isRunning = true ; // tell if the server is alive or not 
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // listen to the user input 
	private ClientProcessing cp; // describe the treatment of one client -> see Class 
	private Thread delegate ; // thread to treat a client separately
	private Thread listenEnd; // Thread that check the end of the server 
	private int ServerPort = 9999; // port used 
	private Socket ClientConnected; // Socket of the client 

	/* Getter & Setter */

	public ClientProcessing getCp() {
		return cp;
	}

	public Socket getClientConnected() {
		return ClientConnected;
	}

	/* Constructor */

	public Server() throws IOException {
		socketserver = new ServerSocket(ServerPort);
	}

	public Server(int port) throws IOException {
		socketserver = new ServerSocket(port);
	}

	/* Methods */

	/**
	 * This method accept a new client and delegate the task to a new Thread
	 * By doing that, the server is "multithread" as he can threat multi-clients requests 
	 * at the same time
	 * 
	 * The class ClientProcessing describe the treatment of one client request 
	 */
	public void AcceptConnection(){
		try {
			ClientConnected = this.socketserver.accept();
			System.out.println("Connected");
			delegate = new Thread(new ClientProcessing(ClientConnected));	
			delegate.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method lauch a new Thread that will check if we wrote "exit"
	 * in order to end the server
	 * @see EndServer()
	 */
	public void EndVerification() {
		listenEnd = new Thread(new Runnable() {
			public void run() {
				do {
					try {
						if(in.readLine().toLowerCase().equals("exit")) {
							EndServer();
						}
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (isRunning);

			}
		});
		listenEnd.start();
	}
	
	/**
	 * This method shutdown the Server by closing the ServerSocket
	 */
	public void EndServer() { 
		try {
			isRunning = false ;
			socketserver.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method describe the server operation
	 * The server is running infinitely 
	 * He just accept new client or check if we ask him to shutdown 
	 */
	public void run() {		
		while(isRunning) {	
			EndVerification();
			AcceptConnection();			
		}  
	}
	
	/**
	 * Main class that start the server 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Thread startServer = new Thread(new Server());
			startServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}