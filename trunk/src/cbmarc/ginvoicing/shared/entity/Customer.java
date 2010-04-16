/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Customer extends Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private String contact;

	@Persistent
	private String fax;

	@Persistent
	private String notes;
	
	@Persistent
	private Boolean enbaled;
	
	/**
	 * 
	 */
	public Customer() {
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the enbaled
	 */
	public Boolean getEnbaled() {
		return enbaled;
	}

	/**
	 * @param enbaled the enbaled to set
	 */
	public void setEnbaled(Boolean enbaled) {
		this.enbaled = enbaled;
	}
	
	public String toString() {
		return "Info { "
			+ "id: " + id
			+ " }";
	}
	
}
