

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/** Représente la base de données videos. Fournit une connexion à
 * cette base (via <code>getConnection()</code>.
 *
 * @author plasse
 */
public class Database {

  /** Code erreur MySQL quand le serveur est inaccessible */
  public static final int SERVER_OFF = 0;

  /** Code erreur MySQL pour "duplicate entry" */
  public static final int DOUBLON = 1062;

  /** Code erreur MySQL pour
   * "Cannot delete or update a parent row: a foreign key
   * constraint fails " */
  public static final int ROW_IS_REFERENCED = 1451;

 /** Code erreur MySQL pour "Cannot add or update
  * a child row: a foreign key constraint fails" */
  public static final int FOREIGN_KEY_NOT_FOUND = 1452;

  protected static final String ORACLE_DRIVER_NAME
      = "oracle.jdbc.driver.OracleDriver";
  protected static final String DERBY_DRIVER_NAME
      = "org.apache.derby.jdbc.ClientDriver";
  protected static final String MYSQL_DRIVER_NAME
      = "com.mysql.jdbc.Driver";
  protected static String DRIVER_NAME, URL;
  protected static final String DB_NAME = "PDS_Gestion_des_stocks";
  protected static final String USER = "ahamdi";
  protected static final String PASSWORD = "";

  /** Positionne la base de données comme étant l'instance du serveur
   * Oracle de l'ESIAG. Charge le pilote Oracle.
   */
  public static void setOracleEsiag() throws InstantiationException {
    URL = "jdbc:oracle:oci8:@localhost:1521:xe";
//    URL = "jdbc:oracle:thin:@eridan:1521/orcl";
    if (!ORACLE_DRIVER_NAME.equals(DRIVER_NAME)) {
      DRIVER_NAME = ORACLE_DRIVER_NAME;
      setDriver();
    }
  }

  /** Positionne la base de données comme étant l'instance Oracle de
   * la machine locale (localhost). Charge le pilote Oracle.
   */
  public static void setOracleLocalhost() throws InstantiationException {
    URL = "jdbc:oracle:oci8:@localhost:1521:XE";
//    URL = "jdbc:oracle:thin:@localhost:1521/XE";
    if (!ORACLE_DRIVER_NAME.equals(DRIVER_NAME)) {
      DRIVER_NAME = ORACLE_DRIVER_NAME;
      setDriver();
    }
  }

  /** Positionne la base de données comme étant la base DB_NAME sous
   * derby, sur localhost. Charge le pilote Derby.
   */
  public static void setDerby() throws InstantiationException {
    URL = "jdbc:derby://localhost:1527/" + DB_NAME;
    if (!DERBY_DRIVER_NAME.equals(DRIVER_NAME)) {
      DRIVER_NAME = DERBY_DRIVER_NAME;
      setDriver();
    }
  }

  /** Positionne la base de données comme étant la base DB_NAME sous
   * MySQL, sur localhost. Charge le pilote MySQL.
   */
  public static void setMySQL() throws InstantiationException {
    URL = "jdbc:mysql://localhost/" + DB_NAME;
    if (!MYSQL_DRIVER_NAME.equals(DRIVER_NAME)) {
      DRIVER_NAME = MYSQL_DRIVER_NAME;
      setDriver();
    }
  }

  public enum SortOrder {
    ASC, DESC;
  }

  /** Chargement du pilote. Doit être exécutée UNE FOIS, au lancement
   * de l'application (c'est pourquoi on en fait parfois un bloc
   * static).
   */
  private static void setDriver() throws InstantiationException {
    try {
      Class.forName(DRIVER_NAME).newInstance();
      System.out.printf("*** Driver %s loaded.\n", DRIVER_NAME);
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException exc) {
      // Depuis java 1.7, on peut rassembler ainsi les exceptions
      exc.printStackTrace();
      throw new InstantiationException(exc.getMessage());
    }
  }

  /** Fournit une connexion à la base de données. Ne fait pas appel à
   * un pool de connexion, même si cela est envisageable plus tard (ne
   * changerait rien à l'appel de la méthode)
   * <br><strong>Requiert</strong> que le pilote soir chargé
   *
   * @throws java.sql.SQLException
   */
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", USER, PASSWORD);
  }

  /** Exécuter un fichier SQL sur la base de données. Les instructions
   * SQL y sont toutes terminées par un point virgule. Les lignes
   * vides et les espaces après les points virgules sont ignorés.
   *
   * @param path chemin du fichier SQL
   * @throws SQLException
   * @throws IOException
   */
  public static void loadSQL(String path) throws SQLException, IOException {
    // Analyseur de fichier
    Scanner scanner = null;
    Connection connexion = null;
    Statement ordre = null;
    try {
      connexion = getConnection();
      ordre = connexion.createStatement();
      scanner = new Scanner(new File(path));
      // Le delimiteur de blocs sera le point virgule suivi de 0 a n espaces
      scanner.useDelimiter(";\\s*");
      // On boucle sur chaque bloc detecté
      while (scanner.hasNext()) {
        // Recuperer l'instruction SQL
        String sql = scanner.next();
        System.out.println(sql);
        ordre.executeUpdate(sql);
      }
    }
    catch (SQLException exc) {
      throw exc;
    }
    catch (IOException exc) {
      throw exc;
    } finally {
      if (ordre != null) {
        ordre.close();
      }
      if (connexion != null) {
        connexion.close();
      }
      if (scanner != null) {
        scanner.close();
      }
    }
  }
}
