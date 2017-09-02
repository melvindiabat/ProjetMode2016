package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe permettant la suppression d'un modele de la BDD
 * @author melvin
 *
 */
public class Delete {

	/**
	 * supprime le modele avec comme nom le parametre
	 * @param name
	 */
	public Delete(String name) {
		try {
			delete(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void delete(String name) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			String query = "delete from modele where nom='"+name+"';";
			statement.executeUpdate(query);
			System.out.println(name + " a bien été supprimé");
			
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(connection != null)
					connection.close();
			}
			catch(SQLException e)
			{
				// connection close failed.
				System.err.println(e);
			}
		}
	}
	
	public static void main(String[] args) {
		new Delete(args[0]);
	}
}
