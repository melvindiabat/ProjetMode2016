package bdd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe permettant la modification d'un modele
 * @author melvin
 *
 */
public class Update {

	
	private String nom;
	private String chemin;
	private String keyword;
	private int nbModify;
	
	/**
	 * Modifie le modele avec les parametres
	 * @param nom
	 * @param chemin
	 * @param keyword
	 * @param src
	 */
	public Update(String nom, String chemin, String keyword, String src) {
		this.nom = nom;
		this.chemin = chemin;
		this.keyword = keyword;
		nbModify = 0;
		if(!nom.isEmpty())  nbModify++;
		if(!chemin.isEmpty()) nbModify++;
		if(!keyword.isEmpty()) nbModify++;
		try {
			update(src);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void update(String src) throws ClassNotFoundException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;
		try
		{
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:data/mode.db");
			Statement statement = connection.createStatement();
			
//			String insert = "insert into modele values('" + nom + "','"+chemin+"',date('now'),'" + keyword + "');";
			
			String update = "update modele set ";
			if(!nom.isEmpty()){
				update += "nom='" + nom + "'";
				if(nbModify>0){
					update+=",";
					nbModify--;
				}
			}
			if(!chemin.isEmpty()){
				update += "chemin='" + chemin +"'";
				if(nbModify>0){
					update+=",";
					nbModify--;
				}
			}
			if(!keyword.isEmpty()){
				update += "keywords='"+ keyword  +"'";
			}
			update += "where nom='" + src + "';";
			statement.executeUpdate(update);
		
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
