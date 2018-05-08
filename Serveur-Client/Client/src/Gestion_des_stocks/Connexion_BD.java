package Gestion_des_stocks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//classe de connexion à la BDD
//le driver se trouve dans C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib
public class Connexion_BD {
	protected static String url = "jdbc:oracle:ahamdi:@//localhost:1521/XE";
	protected static String user = "ahamdi";
	protected static String passwd = "";

	public Connexion_BD() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver O.K.");

			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion réussie !");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  public static Connection getConnection() throws SQLException {
		    return DriverManager.getConnection(url,user,passwd);
		  }
}