
/*
 * 
 * 13-01-2018
 * PDS : Projet MASK
 * 
 * Classe servant à réaliser un pool de connexion
 * Le pool de connexion permet de réutiliser les connexions physiques à la base de données ORACLE 
 * qui prennent beaucoup de temps et les réaffecte pour des nouvelles demandes de connexions côté clients. 
 * Il est possible de l’implémenter avec JDBC.
 * 
 * @AmineHamdi
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnexionPool {
	
  public static void main(String[] args) throws Exception {
	//On crée notre poolDataSource qui va produire des instances de connexion et les stocker
    OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
    //On met nos paramètres
    ocpds.setURL("jdbc:oracle:thin:@localhost:1521:ORCL");
    ocpds.setUser("user");
    ocpds.setPassword("password");
    //On crée un object de type PooledConnection afin d'établir une connexion à la base
    PooledConnection pc_1 = ocpds.getPooledConnection();
    //On crée une instance de Connection et on la stocke dans notre PooledConnection. Ainsi, si la connexion est interrompue,
    //la connexion "physique" restera maintenue à travers PooledConnection
    Connection conn_1 = pc_1.getConnection();
    Statement stmt = conn_1.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT count(*) FROM v$session WHERE username = 'SYS'");
    rs.next();
    String msg = "Total connections after ";
    System.out.println(msg + "conn_1: " + rs.getString(1));
    

    conn_1.close();
    pc_1.close();
  }
}
