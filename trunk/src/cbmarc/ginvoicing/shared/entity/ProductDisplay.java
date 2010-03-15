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
public class ProductDisplay implements Serializable {
	
	private String id;
	private String name;

	/**
	 * 
	 */
	public ProductDisplay() {
	}

	/**
	 * @param name
	 * @param description
	 * @param price
	 * @param category
	 */
	public ProductDisplay(String id, String name) {
		this.id = id;
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}