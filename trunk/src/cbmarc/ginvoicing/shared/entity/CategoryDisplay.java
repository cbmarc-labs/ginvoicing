/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class CategoryDisplay implements Serializable {

	private String id;
	private String description;

	/**
	 * @param id
	 * @param description
	 */
	public CategoryDisplay(String id, String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
