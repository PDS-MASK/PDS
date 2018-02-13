import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

	public static void main(String[] args) {
		// TODO Auto-generated method stubb

		
	Connection connection = null;
	
	try {
		
	connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","PDS","toto");	
	Statement stmt = null;
	stmt = connection.createStatement();
	ResultSet rs = stmt.executeQuery("Select * from personne");
	ResultSetMetaData rsmd = rs.getMetaData();
	
	int rowsSubmit = 0;

	int columnsNumber = rsmd.getColumnCount();
	while (rs.next()) {
		
		rowsSubmit++;
		
		for (int i = 1; i<= columnsNumber; i++)
			
		{
			
			System.out.print(" "+ rs.getString(i));
			
		}
		System.out.println();
	}
	} catch (SQLException e) {
		
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}
		
	}

}
