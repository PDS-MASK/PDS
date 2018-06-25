package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class PoolConnexion implements PoolConnexion_Constructor {

	private static class Pool_Connexion {

		private String url;
		private String user;
		private String password;

		public Pool_Connexion() {

			this.url = "jdbc:oracle:thin:@localhost:1521:xe";
			this.user = "PDS";
			this.password = "toto";
		}

		public Connection getConnection() {
			try {
				return DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private ArrayList<Connection> pool;
	private final int MAX = 20;

	public PoolConnexion() {
		this.pool = new ArrayList<Connection>();
		for (int i = 0; i < MAX; i++) {
			pool.add(new Pool_Connexion().getConnection());
		}
	}

	public ArrayList<Connection> getPool() {
		return pool;
	}

	public boolean checkConnection() {
		if (pool.isEmpty())
			return false;
		return true;
	}

	public Connection getConnection() {
		if (checkConnection()) {
			
			Connection c = pool.remove(count()-1);
			return c;
		}
		return null;
	}
	
	public int count() {
		int i = 0 ;
		for (int j = 0; j < pool.size(); j++) {
			i++;
		}
		return i ;
	}

	public void release(Connection c) {
		pool.add(c);
	}
	
	public void closeEverything() {

		for (Connection c : pool) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int max() {
		return MAX;
	}
}