package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbConnection;
import dto.WsAvailabilityDTO;

/**
 * Classe d'accès aux données pour les tranches horaires de disponibilité des
 * machines.
 */
public class WsAvailabilityDAO {

	/**
	 * Récupère les tranches horaires de disponibilité pour une machine donnée.
	 * 
	 * @param id
	 *            L'ID de la machine.
	 * @return Une liste de {@link WsAvailabilityDTO}.
	 */
	public static List<WsAvailabilityDTO> retrieveAvailabilitiesByWsId(int id) {
		List<WsAvailabilityDTO> wsAvailabilities = new ArrayList<WsAvailabilityDTO>();

		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();

			ResultSet resultat = stat
					.executeQuery("SELECT * FROM ws_availabilities WHERE id_ws='"
							+ id + "'");

			while (resultat.next()) {
				WsAvailabilityDTO newAvailability = new WsAvailabilityDTO();
				newAvailability.setId(id);
				newAvailability.setId_ws(resultat.getInt("id_ws"));
				newAvailability.setStart_time(resultat.getDate("start_time"));
				newAvailability.setStop_time(resultat.getDate("stop_time"));
				newAvailability.setWeekday(resultat.getInt("weekday"));
				wsAvailabilities.add(newAvailability);
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

		return wsAvailabilities;
	}

}
