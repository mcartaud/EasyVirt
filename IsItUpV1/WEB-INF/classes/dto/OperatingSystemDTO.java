package dto;

/**
 * Classe de DTO pour les OS.
 */
public class OperatingSystemDTO {

	// Fields in table
	/** L'ID de l'OS. */
	protected int id;

	/** Le type d'OS. */
	protected String label;

	/**
	 * Constructeur.
	 */
	public OperatingSystemDTO() {
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

}
