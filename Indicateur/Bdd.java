package indicateur;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


public class Bdd {
	  /** Code erreur Oracle quand le serveur est inaccessible */
	  public static final int SERVER_OFF = 0;

	  /** Code erreur Oracle pour "duplicate entry" */
	  public static final int DOUBLON = 1;

	  /** Code erreur Oracle pour
	   * "Cannot delete or update a parent row: a foreign key
	   * constraint fails " */
	  public static final int ROW_IS_REFERENCED = 2292;

	 /** Code erreur Oracle pour "Cannot add or update
	  * a child row: a foreign key constraint fails" */
	  public static final int FOREIGN_KEY_NOT_FOUND = 2291;

	  protected static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	  protected static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	  protected static final String DB_NAME = "BDD";
	  protected static final String USER = "MOI";
	  protected static final String PASSWORD = "moi";

	  /** Positionne la base de données comme étant l'instance Oracle de
	   * la machine locale (localhost). Charge le pilote Oracle.
	   */
	  public enum SortOrder {
	    ASC, DESC;
	  }

	  static {
	   
	    try {
	      Class.forName(DRIVER_NAME).newInstance();
	      System.out.println("*** Driver loaded.");
	    }
	    catch (ClassNotFoundException e) {
	      System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
	    }
	    catch (InstantiationException e) {
	      System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
	      System.err.println(e.getMessage());
	    }
	    catch (IllegalAccessException e) {
	      System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
	      System.err.println(e.getMessage());
	    }
	  }

	 
	  public static Connection getConnection() throws SQLException {
	    return DriverManager.getConnection(URL, USER, PASSWORD);
	  }
}
