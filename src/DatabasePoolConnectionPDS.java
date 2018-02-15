/*
 * 
 * 13-02-2018 1.1
 * PDS : Projet MASK
 * 
 * Interface servant à réaliser un pool de connexion testssss
 * Le pool de connexion permet de réutiliser les connexions physiques à la base de données ORACLE 
 * qui prennent beaucoup de temps et les réaffecte pour des nouvelles demandes de connexions côté clients. 
 * Il est possible de l’implémenter avec JDBC.
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

	
	//Construit un pool de connexion avec comme paramètre le PoolDataSource qui va stocker les connexions,
	//le nombre maximum de connexion et le délai d'attente avant d'ouvrir une nouvelle connexion (en seconde)
	/* 
	 * récup les paramètres de connection et se co à la BD
	 * try : créer une connexion qui se rattache à la bd et est ajoutée au pool
	 * 
	 */
	Connection DBConnexionPoolDataSource(OracleConnectionPoolDataSource ocpds);
	
	
	//Intercepter une connexion entrante et l'intégrer au Pool
	Connection getConnection();
	/*
	 * 
	 */
	
	void resetPoolDataSource(ConnectionPoolDataSource dataSource);
	
	int getMaxConnections();
	
	
	void closeConnection();

}
