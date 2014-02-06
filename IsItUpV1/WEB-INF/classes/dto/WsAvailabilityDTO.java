package dto;

import java.util.Date;

/**
 * Classe de DTO pour les heures de disponibilités des machines.
 */
public class WsAvailabilityDTO {

	// Fields in table
	/** L'ID associé à la siponibilité. */
	protected int id;

	/** L'ID de la machine. */
	protected int id_ws;

	/** Le jour de la semaine représenté par un entier. */
	protected int weekday;

	/** La date de début de disponibilité. */
	protected Date start_time;

	/** La date de fin de disponibilité. */
	protected Date stop_time;

	/**
	 * Constructeur.
	 */
	public WsAvailabilityDTO() {
	}

	/**
	 * Setter pour id.
	 * 
	 * @return Le id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter pour id.
	 * 
	 * @param id
	 *            Le id à setter.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setter pour id_ws.
	 * 
	 * @return Le id_ws.
	 */
	public int getId_ws() {
		return id_ws;
	}

	/**
	 * Setter pour id_ws.
	 * 
	 * @param id_ws
	 *            Le id_ws à setter.
	 */
	public void setId_ws(int id_ws) {
		this.id_ws = id_ws;
	}

	/**
	 * Setter pour weekday.
	 * 
	 * @return Le weekday.
	 */
	public int getWeekday() {
		return weekday;
	}

	/**
	 * Setter pour weekday.
	 * 
	 * @param weekday
	 *            Le weekday à setter.
	 */
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}

	/**
	 * Setter pour start_time.
	 * 
	 * @return Le start_time.
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * Setter pour start_time.
	 * 
	 * @param start_time
	 *            Le start_time à setter.
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * Setter pour stop_time.
	 * 
	 * @return Le stop_time.
	 */
	public Date getStop_time() {
		return stop_time;
	}

	/**
	 * Setter pour stop_time.
	 * 
	 * @param stop_time
	 *            Le stop_time à setter.
	 */
	public void setStop_time(Date stop_time) {
		this.stop_time = stop_time;
	}

}
