package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbConnection;

/**
 * Classe d'accès aux données pour les systèmes d'exploitation.
 */
public class OperatingSystemDAO {

	/**
	 * Enregistre en base un nouveau système d'exploitation. On souhaite stocker
	 * la liste des systèmes d'exploitation présents dans le parc surveillé.
	 * 
	 * @param label
	 *            Nom donné au nouveau système d'exploitation.
	 */
	public static void insertOperatingSystem(String label) {

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			stat.executeUpdate("INSERT INTO `operating_systems`(`label`) VALUES ('"
					+ label + "')");
			stat.close();
			conn.close();
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Extrait de la base de données l'identifiant d'un système d'exploitation à
	 * partir de sa dénomination.
	 * 
	 * @param label
	 *            Le nom du système d'exploitation.
	 * @return L'identifiant du système d'exploitation.
	 */
	public static int retrieveOsIdByLabel(String label) {
		int os_id = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT * FROM `operating_systems` WHERE label='"
							+ label + "'");

			while (resultSet.next()) {
				os_id = resultSet.getInt("id");
			}

			resultSet.close();
			stat.close();
			conn.close();
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return os_id;
	}

	/**
	 * Extrait de la base de données le label d'un os a partir de son id.
	 * 
	 * @param idOS
	 *            Le nom du système d'exploitation.
	 * @return L'identifiant du système d'exploitation.
	 */
	public static String retrieveOsLabelByid(int idOS) {
		String osLabel = "inconnu";
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT * FROM `operating_systems` WHERE id='"
							+ idOS + "'");

			if (resultSet != null) {
				resultSet.next();
				osLabel = resultSet.getString("label");
			}

			resultSet.close();
			stat.close();
			conn.close();
		} catch (SQLException ex) {
			while (ex != null) {
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return osLabel;
	}

}
