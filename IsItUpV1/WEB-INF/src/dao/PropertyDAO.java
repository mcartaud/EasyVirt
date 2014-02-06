package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DbConnection;
import dto.PropertyDTO;

public class PropertyDAO {

	/**
	 * Récupère la valeur d'une propriété à partir du nom de la propriété.
	 * 
	 * @param name
	 *            Le nom de la propriété.
	 * @return PropertyDTO
	 */
	public static PropertyDTO retrievePropertyByName(String name) {

		PropertyDTO propertyDTO = new PropertyDTO();

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT * FROM `properties` WHERE name='"
							+ name + "'");

			if (resultSet != null) {
				resultSet.next();
				propertyDTO.setId(resultSet.getInt("id"));
				propertyDTO.setName(resultSet.getString("name"));
				propertyDTO.setValue(resultSet.getInt("value"));
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

		return propertyDTO;
	}

	/**
	 * Modifie la valeur d'une propriété en base.
	 * 
	 * @param name
	 *            Le nom de la propriété.
	 * @param value
	 *            La nouvelle valeur de la propriété.
	 */
	public static void updateProperty(String name, int value) {

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			stat.executeUpdate("UPDATE `properties` SET `value`=" + value
					+ " WHERE name='" + name + "'");

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

}
