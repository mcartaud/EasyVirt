package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connection à la base de données.
 */
public class DbConnection {

	/**
	 * Obtient une connexion à la base de données à partir des paramètres
	 * spécifiés dans le fichier connect.conf et renvoie la connexion à cette
	 * base de données.
	 */
	public static Connection getConnection() throws SQLException, IOException {
		// Properties props = new Properties();
		// FileInputStream in = new FileInputStream(
		// "WebContent/WEB-INF/src/database.properties");
		// props.load(in);
		// in.close();

		// String drivers = props.getProperty("jdbc.drivers");
		// if (drivers != null)
		System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		// String url = props.getProperty("jdbc.url");
		// String username = props.getProperty("jdbc.username");
		// String password = props.getProperty("jdbc.password");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/isitup",
				"root", "");
	}

}
