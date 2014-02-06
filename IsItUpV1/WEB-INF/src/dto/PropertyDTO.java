package dto;

/**
 * Classe de DTO pour les propriétés.
 */
public class PropertyDTO {

	// Fields in table
	/** L'ID de la propriété. */
	protected int id;

	/** Le nom de la propriété. */
	protected String name;

	/** La valeur de la propriété. */
	protected int value;

	/**
	 * Constructeur.
	 */
	public PropertyDTO() {
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
	 * Setter pour name.
	 * 
	 * @return Le name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter pour name.
	 * 
	 * @param name
	 *            Le name à setter.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter pour value.
	 * 
	 * @return Le value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter pour value.
	 * 
	 * @param value
	 *            Le value à setter.
	 */
	public void setValue(int value) {
		this.value = value;
	}

}
