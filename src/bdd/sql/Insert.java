package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe permettant d'inserer un modele dans la base de donnée
 * @author melvin
 *
 */
public class Insert {

	private String nom;
	private String chemin;
	private String keyword;
	
	/**
	 * insert le modele avec son nom,son chemin et ses mots-cles
	 * @param nom
	 * @param chemin
	 * @param keyword
	 */
	public Insert(String nom, String chemin, String keyword) {
		this.nom = nom;
		this.chemin = chemin;
		this.keyword = keyword;
		try {
			insert();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void insert() throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			
			String insert = "insert into modele values('" + nom + "','"+chemin+"',date('now'),'" + keyword + "');";
			statement.executeUpdate(insert);
		
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
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

}
