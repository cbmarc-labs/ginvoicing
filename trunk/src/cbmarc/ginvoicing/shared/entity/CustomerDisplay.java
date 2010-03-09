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
public class CustomerDisplay implements Serializable {
	private String id;
	private String name;
	
	/**
	 * 
	 */
	public CustomerDisplay() {
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public CustomerDisplay(String id, String name) {
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
