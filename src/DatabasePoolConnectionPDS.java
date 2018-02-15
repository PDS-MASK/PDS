/*
 * 
 * 13-02-2018 1.1
 * PDS : Projet MASK
 * 
 * Interface servant � r�aliser un pool de connexion testssss
 * Le pool de connexion permet de r�utiliser les connexions physiques � la base de donn�es ORACLE 
 * qui prennent beaucoup de temps et les r�affecte pour des nouvelles demandes de connexions c�t� clients. 
 * Il est possible de l�impl�menter avec JDBC.
 * 
 * @AmineHamdi
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

import javax.sql.ConnectionPoolDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public interface DatabasePoolConnectionPDS {
	
	String dbserver = "jdbc";
	String dbuser = "test";
	String dbpassword = "test";

	
	//Construit un pool de connexion avec comme param�tre le PoolDataSource qui va stocker les connexions,
	//le nombre maximum de connexion et le d�lai d'attente avant d'ouvrir une nouvelle connexion (en seconde)
	/* 
	 * r�cup les param�tres de connection et se co � la BD
	 * try : cr�er une connexion qui se rattache � la bd et est ajout�e au pool
	 * 
	 */
	Connection DBConnexionPoolDataSource(OracleConnectionPoolDataSource ocpds);
	
	
	//Intercepter une connexion entrante et l'int�grer au Pool
	Connection getConnection();
	/*
	 * 
	 */
	
	void resetPoolDataSource(ConnectionPoolDataSource dataSource);
	
	int getMaxConnections();
	
	
	void closeConnection();

}
