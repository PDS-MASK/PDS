import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class PDS_Pool implements DatabasePoolConnectionPDS {

	@Override
	public Connection DBConnexionPoolDataSource(ConnectionPoolDataSource dataSource) {
		try {
			//On crée un object de type PooledConnection afin d'établir une connexion à la base
		    PooledConnection pc_1 = ocpds.getPooledConnection();
		    //On crée une instance de Connection et on la stocke dans notre PooledConnection. Ainsi, si la connexion est interrompue,
		    //la connexion "physique" restera maintenue à travers PooledConnection
		    Connection conn_1 = pc_1.getConnection();
		    return conn_1;
		} catch (SQLException e) {
			
		}
		
		return Connection;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetPoolDataSource(ConnectionPoolDataSource dataSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxConnections() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

}
