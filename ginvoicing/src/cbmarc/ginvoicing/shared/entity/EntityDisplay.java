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
public class EntityDisplay implements Serializable {
	private String[] data;
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private EntityDisplay() {
	}
	
	/**
	 * @param id
	 * @param name
	 */
	public EntityDisplay(String[] data) {
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public String[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String[] data) {
		this.data = data;
	}
	
}
