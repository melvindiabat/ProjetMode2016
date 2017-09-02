package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe permettant l'affichage d'un modele 
 * @author melvin
 *
 */
public class SelectOne {
	
	private String name;
	private String pwd;
	private String date;
	private String keywords;
	private boolean error = false;
	
	/**
	 * affichage du modele passe en parametre
	 * @param nom
	 */
	public SelectOne(String nom) {
		try {
			selectOne(nom);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void selectOne(String nom) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			String query = "Select * from modele where nom='"+ nom + "';";
			ResultSet rs = statement.executeQuery(query);
			
			if(rs.next()){
				name = rs.getString(1);
				pwd = rs.getString(2);
				date = rs.getString(3);
				keywords = rs.getString(4);
			}else{
				error = true;
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


	public boolean isError() {
		return error;
	}


	public String getDate() {
		return date;
	}


	public String getName() {
		return name;
	}


	public String getPwd() {
		return pwd;
	}


	public String getKeywords() {
		return keywords;
	}
	
}
