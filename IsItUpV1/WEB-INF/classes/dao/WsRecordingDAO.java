package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import db.DbConnection;
import dto.WsRecordingDTO;

/**
 * Classe d'accès aux données pour les machines pingées sur un réseau à un
 * moment t.
 */
public class WsRecordingDAO {

	/**
	 * Récupère toutes les machines qui ont été pingées à un moment t sur le
	 * réseau. Correspond aux machines présentes dans la base des
	 * enregistrements.
	 * 
	 * @return La liste des machines ayant été pingées.
	 */
	public static List<WsRecordingDTO> retrieveAllRecordings() {

		List<WsRecordingDTO> wsRecordings = new ArrayList<WsRecordingDTO>();

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultat = stat
					.executeQuery("SELECT * FROM ws_recordings");

			while (resultat.next()) {
				WsRecordingDTO myRecording = new WsRecordingDTO();
				myRecording.setId_ws(resultat.getInt("id_ws"));
				myRecording.setTime(resultat.getDate("time"));
				myRecording.setContacted(resultat.getBoolean("contacted"));
				myRecording.setState(resultat.getInt("state"));
				wsRecordings.add(myRecording);
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

		return wsRecordings;
	}

	/**
	 * Récupère les enregistrements des différents ping d'une machine données.
	 * 
	 * @param id_ws
	 *            L'ID de la machine pour laquelle on veut récupérer les
	 *            enregistrements.
	 * @return La liste des enregistrements d'une machine données.
	 */
	public static List<WsRecordingDTO> retrieveRecordingsByIdWs(int id_ws) {
		List<WsRecordingDTO> wsRecordings = new ArrayList<WsRecordingDTO>();

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultat = stat
					.executeQuery("SELECT * FROM ws_recordings WHERE id_ws='"
							+ id_ws + "'");

			while (resultat.next()) {
				WsRecordingDTO myRecording = new WsRecordingDTO();
				myRecording.setId(id_ws);
				myRecording.setId_ws(resultat.getInt("id_ws"));
				myRecording.setTime(resultat.getDate("time"));
				myRecording.setContacted(resultat.getBoolean("contacted"));
				myRecording.setState(resultat.getInt("state"));
				wsRecordings.add(myRecording);
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

		return wsRecordings;
	}

	/**
	 * Insert un enregistrement pour une machine.
	 * 
	 * @param id_ws
	 *            L'ID de la machine.
	 * @param time
	 *            La date à laquelle la machine a été pinguée.
	 * @param contacted
	 *            Un booléen indiquant si on a réussi à pinguer la machine.
	 * @param state
	 *            L'état de la machine lorsqu'elle a été pinguée (0 : inconnue,
	 *            1 : allumée, 2 : éteinte, 3 : en veille).
	 */
	public static void insertRecording(int id_ws, Date time, Boolean contacted,
			int state) {
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String timeConverted = sdf.format(time);

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			stat.executeUpdate("INSERT INTO `ws_recordings`(`id_ws`, `time`, `contacted`, `state`) VALUES ('"
					+ id_ws
					+ "','"
					+ timeConverted
					+ "',"
					+ contacted
					+ ",'"
					+ state + "')");

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
	 * Récupère le nombre de machines allumées à une date t.
	 * 
	 * @param date
	 *            La date souhaitée.
	 * @return Le nombre de machines allumées.
	 */
	public static int retrieveNumberWorkstationsUpPerDay(Date date) {
		SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd");
		String year = yearFormat.format(date);
		String month = monthFormat.format(date);
		String day = dayFormat.format(date);

		int number = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT COUNT(DISTINCT id_ws) FROM `ws_recordings` WHERE state=1 AND YEAR(time) = "
							+ year
							+ " AND MONTH(time) = "
							+ month
							+ " AND DAY(time) = " + day);
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
	 * Récupère l'enregistrement correspondant au dernier état d'une machine
	 * donnée.
	 * 
	 * @param id_ws
	 *            L'ID de la machine recherchée.
	 * @return Le dernier enregistrement de la machine.
	 */
	public static WsRecordingDTO retrieveLastRecording(int id_ws) {
		WsRecordingDTO wsLastState = null;

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT id, id_ws, max(time), contacted, state FROM `ws_recordings` WHERE id_ws='"
							+ id_ws + "'");
			while (resultSet.next() && resultSet.getTimestamp("max(time)")!=null) {
				wsLastState = new WsRecordingDTO();
				wsLastState.setId(id_ws);
				wsLastState.setId_ws(resultSet.getInt("id_ws"));
				wsLastState.setTime(resultSet.getTimestamp("max(time)"));
				wsLastState.setContacted(resultSet.getBoolean("contacted"));
				wsLastState.setState(resultSet.getInt("state"));
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

		return wsLastState;
	}

	/**
	 * Récupère le nombre d'heures où les machines ont été allumées alors
	 * qu'elles ne devaient pas l'être, pour un jour donné.
	 * 
	 * @param date
	 *            La date du jour analysé.
	 * @return Un nombre d'heures.
	 */
	public static double retrieveHoursOutOfNormal(Date date) {

		int pingFrequency = PropertyDAO.retrievePropertyByName("ping_frequency").getValue();
		List<Integer> listWsId = new ArrayList<Integer>();
		int intMilliSecOutOfNormal = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultSet = stat
					.executeQuery("SELECT id FROM `workstations`");
			while (resultSet.next()) {
				listWsId.add(resultSet.getInt(1));
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

		SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd");
		SimpleDateFormat weekdayFormat = new java.text.SimpleDateFormat("EEEE");
		String year = yearFormat.format(date);
		String month = monthFormat.format(date);
		String day = dayFormat.format(date);
		String weekday = weekdayFormat.format(date);

		HashMap<Time, Integer> records = new HashMap<Time, Integer>();
		List<Time> listRecords = new ArrayList<Time>();
		
		for (int i = 0; i < listWsId.size(); i++) {
			try {
				Connection conn = DbConnection.getConnection();
				Statement stat = conn.createStatement();

				ResultSet resultSet = stat
						.executeQuery("SELECT `time`, `state` FROM `ws_recordings` WHERE state=1 AND id_ws='"
								+ listWsId.get(i)
								+ "' AND YEAR(time) = "
								+ year
								+ " AND MONTH(time) = "
								+ month
								+ " AND DAY(time) = " + day + " ORDER BY id");

				while (resultSet.next()) {
					records.put(resultSet.getTime(1), resultSet.getInt(2));
					listRecords.add(resultSet.getTime(1));
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
			List<Time> listAvailabilities = new ArrayList<Time>();
			try {
				Connection conn = DbConnection.getConnection();
				Statement stat = conn.createStatement();

				ResultSet resultSet = stat
						.executeQuery("SELECT start_time, stop_time FROM ws_availabilities WHERE id_ws='"
								+ listWsId.get(i) + "'");

				while (resultSet.next()) {
					listAvailabilities.add(resultSet.getTime(1));
					listAvailabilities.add(resultSet.getTime(2));
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

			if (listAvailabilities.size() == 0) {

				try {
					Connection conn = DbConnection.getConnection();
					Statement stat = conn.createStatement();

					ResultSet resultSet = stat
							.executeQuery("SELECT value FROM properties WHERE name LIKE '"
									+ weekday + "%'");

					while (resultSet.next()) {
						listAvailabilities.add(java.sql.Time.valueOf(resultSet
								.getString(1) + ":00:00"));
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
			}
			for (Time key : records.keySet()) {
				Boolean isNormal = false;
				for (int j = 0; j < listAvailabilities.size(); j = j + 2) {
					if (key.after(listAvailabilities.get(j))
							&& key.before(listAvailabilities.get(j + 1))
							|| records.get(key) == 2) {
						isNormal = true;
						break;
					}
				}
				if (!isNormal) {
					intMilliSecOutOfNormal++;
				}
			}
			records.clear();
			listAvailabilities.clear();
		}
		return ((double)(intMilliSecOutOfNormal * pingFrequency))/60;
	}
}
