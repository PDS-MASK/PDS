package server;

import java.sql.Connection;


public interface PoolConnexion_Constructor {

	/**
	 * Checks if there is already a free connection that can be used by a customer
	 * 
	 * @return true if there is a free connection, false if not
	 */
	public boolean checkConnection();

	/**
	 * Assigns an active and free connection from the Pool to a client
	 * 
	 * @return connection if there is one available
	 */
	public Connection getConnection();

	/**
	 * Closes all the active connections in the list
	 */
	public void closeEverything();
	
	/**
	 * Free up a connection by putting it back to the pool 
	 */
	public void release(Connection c) ;
	
	/**
	 * Return the max of the connection that the pool is able to give
	 */
	public int max() ;
	
	/**
	 * 
	 */
	
}