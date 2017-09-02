package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
/**
 * Classe permettant l'affichage de modele avec leur nom ou un mot clé
 * @author melvin
 *
 */
public class Find {

	private String keywords;
	/**
	 * affiche les differents modeles de la liste
	 * @param dlm
	 * @param objects des objects a afficher
	 */
	public Find(DefaultListModel<String> dlm, Object[] objects) {
		try {
			find(dlm, objects);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void find(DefaultListModel<String> dlm, Object[] args) throws ClassNotFoundException
	{
		if(args == null) return;
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			String query = "Select * from modele;";
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				keywords = rs.getString(4);
				for(int i = 0; i<args.length; i++){
					if(keywords.contains((String) args[i])){
						if(!dlm.contains(rs.getString(1))){
							dlm.addElement(rs.getString(1));
						}
					}
				}
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
