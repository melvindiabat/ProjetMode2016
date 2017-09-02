package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
/**
 * Classe permettant l'affichage de tous les modeles de la BDD
 * @author melvin
 *
 */
public class SelectAll {

	/**
	 * affiche les modeles de la liste donnee en parametre
	 * @param list
	 */
	public SelectAll(DefaultListModel<String> list) {
		try {
			selectAll(list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void selectAll(DefaultListModel<String> list) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			String query = "Select nom from modele;";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				list.addElement(rs.getString(1));
			}
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
}
