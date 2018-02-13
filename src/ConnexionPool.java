/*
 * 
 * 13-01-2018
 * PDS : Projet MASK
 * 
 * Classe servant � r�aliser un pool de connexion
 * Le pool de connexion permet de r�utiliser les connexions physiques � la base de donn�es ORACLE 
 * qui prennent beaucoup de temps et les r�affecte pour des nouvelles demandes de connexions c�t� clients. 
 * Il est possible de l�impl�menter avec JDBC.
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
	//On cr�e notre poolDataSource qui va produire des instances de connexion et les stocker
    OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
    //On met nos param�tres
    ocpds.setURL("jdbc:oracle:thin:@localhost:1521:ORCL");
    ocpds.setUser("user");
    ocpds.setPassword("password");
    //On cr�e un object de type PooledConnection afin d'�tablir une connexion � la base
    PooledConnection pc_1 = ocpds.getPooledConnection();
    //On cr�e une instance de Connection et on la stocke dans notre PooledConnection. Ainsi, si la connexion est interrompue,
    //la connexion "physique" restera maintenue � travers PooledConnection
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
