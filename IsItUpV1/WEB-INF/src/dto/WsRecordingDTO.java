package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe de DTO pour les enregistrements des machines.
 */
public class WsRecordingDTO {

	private static final String ON = "Allumé";
	private static final String OFF = "Eteint";
	private static final String VEILLE = "Veille";
	private static final String UNKNOWN = "Inconnu";
	
	private static final SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm:ss");
	String dateOut="-";
	String timeOut="";
	
	// Fields in table
	/** L'ID de l'enregistrement. */
	protected int id;

	/** L'ID de la machine. */
	protected int id_ws;

	/** La date de l'enregistrement. */
	protected Date time;

	/** Booléen indiquant si on a contacté la machine ou pas. */
	protected Boolean contacted;

	/** L'état de la machine. */
	protected int state;

	
	public String retrieveState(int state){
		String out="";
		switch (state) {
		case 1: out = ON;  
			break;
		case 2: out = OFF;
			break;
		case 3: out = VEILLE;
			break;
		default:
			out = UNKNOWN;
			break;
		}
		return out;
	}
	
	/**
	 * Constructeur.
	 */
	public WsRecordingDTO() {
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
	 * Setter pour time.
	 * 
	 * @return Le time.
	 */
	public Date getTime() {
		return time;
	}
	
	
	public String getTimeFormatDetail(){
		return timeFormat.format(time);
	}
	
	public String getDateFormatDetail(){
		SimpleDateFormat dateeFormat = new java.text.SimpleDateFormat("dd-MM-yyyy");
		return dateeFormat.format(time);
	}

	/**
	 * Setter pour time.
	 * 
	 * @param time
	 *            Le time à setter.
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Setter pour contacted.
	 * 
	 * @return Le contacted.
	 */
	public Boolean getContacted() {
		return contacted;
	}

	/**
	 * Setter pour contacted.
	 * 
	 * @param contacted
	 *            Le contacted à setter.
	 */
	public void setContacted(Boolean contacted) {
		this.contacted = contacted;
	}

	/**
	 * Setter pour state.
	 * 
	 * @return Le state.
	 */
	public int getState() {
		return state;
	}

	/**
	 * Setter pour state.
	 * 
	 * @param state
	 *            Le state à setter.
	 */
	public void setState(int state) {
		this.state = state;
	}
}
