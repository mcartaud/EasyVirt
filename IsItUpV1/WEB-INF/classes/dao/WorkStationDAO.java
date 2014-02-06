package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DbConnection;
import dto.WorkStationDTO;

/**
 * Classe d'accès aux données pour les machines récupérées du fichier CSV.
 */
public class WorkStationDAO {

	/**
	 * Insert une nouvelle machine en base.
	 * 
	 * @param workstation
	 *            La machine qe l'on souhaite insérer en base.
	 */
	public static void insert(WorkStationDTO workstation) {
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String timeConverted = sdf.format(workstation.getDate());

			String query = "";
			query += "INSERT INTO workstations (ip, date, label, id_os) values (";
			query += "'" + workstation.getIp() + "', ";
			query += "'" + timeConverted + "', ";
			query += "'" + workstation.getLabel() + "', ";
			query += "'" + workstation.getId_os() + "'";
			query += ");";

			stat.executeUpdate(query);
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
	 * Récupère toutes les machines présentes en base.
	 * 
	 * @return La liste des machines présentes en base.
	 */
	public static List<WorkStationDTO> retrieveAll() {
		List<WorkStationDTO> workStations = new ArrayList<WorkStationDTO>();

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultat = stat
					.executeQuery("SELECT * FROM workstations");

			while (resultat.next()) {
				WorkStationDTO newWorkStation = new WorkStationDTO();

				newWorkStation.setId(resultat.getInt("id"));
				newWorkStation.setId_os(resultat.getInt("id_os"));
				newWorkStation.setIp(resultat.getString("ip"));
				newWorkStation.setDate(resultat.getDate("date"));
				newWorkStation.setLabel(resultat.getString("label"));
				workStations.add(newWorkStation);
			}
			resultat.close();
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

		return workStations;
	}

	/**
	 * Supprime toutes les machines en base.
	 */
	public static void deleteAll() {
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			stat.executeUpdate("TRUNCATE TABLE workstations");
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
	 * Supprime la machine associée à l'adresse IP.
	 * 
	 * @param ip_ws
	 *            L'adresse IP de la machine à supprimer de la base.
	 */
	public static void deleteWorkstationByIp(String ip_ws) {
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			stat.executeUpdate("DELETE FROM `workstations` WHERE ip='" + ip_ws
					+ "'");

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
	 * Récupère le nombre de machines en base.
	 * 
	 * @return Le nombre de machines en base.
	 */
	public static int retrieveNumberWorkstations() {
		int number = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT count( * ) FROM `workstations`");
			if (resultSet.next()) {
				number = resultSet.getInt(1);
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

		return number;
	}

	/**
	 * Récupère le nombre de machines en base en fonction de leurs OS.
	 * 
	 * @return Une Map contenant le nombre de machines pour chaque OS.
	 */
	public static HashMap<String, Integer> retrieveNumberWorkstationByOS() {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT operating_systems.label, count(id_os) FROM `workstations`, `operating_systems` WHERE id_os = operating_systems.id group by operating_systems.label");

			while (resultSet.next()) {
				result.put(resultSet.getString(1), resultSet.getInt(2));
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

		return result;
	}
}
