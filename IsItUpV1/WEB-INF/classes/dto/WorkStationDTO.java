package dto;

import java.util.Date;

/**
 * Classe de DTO pour les machines.
 */
public class WorkStationDTO {

	// Fields in table
	/** L'ID de la machine. */
	protected int id;

	/** L'adresse IP de la machine. */
	protected String ip;

	/** La date d'ajout de la machine en base. */
	protected Date date;

	/** Le label de la machine. */
	protected String label;

	/** L'ID de l'OS de la machine. */
	protected int id_os;

	/**
	 * Constructeur.
	 */
	public WorkStationDTO() {
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
	 * Setter pour ip.
	 * 
	 * @return Le ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setter pour ip.
	 * 
	 * @param ip
	 *            Le ip à setter.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Setter pour date.
	 * 
	 * @return Le date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Setter pour date.
	 * 
	 * @param date
	 *            Le date à setter.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Setter pour label.
	 * 
	 * @return Le label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Setter pour label.
	 * 
	 * @param label
	 *            Le label à setter.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Setter pour id_os.
	 * 
	 * @return Le id_os.
	 */
	public int getId_os() {
		return id_os;
	}

	/**
	 * Setter pour id_os.
	 * 
	 * @param id_os
	 *            Le id_os à setter.
	 */
	public void setId_os(int id_os) {
		this.id_os = id_os;
	}

}
