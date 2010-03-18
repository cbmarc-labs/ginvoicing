/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION) //, detachable="true")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;
	
	// date invoice created
	@Persistent
	private Date date;
	
	// foreign key to customer
	@Persistent
	private String customer;
	
	@Persistent //(serialized="true", defaultFetchGroup="true")
	//@Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="quantity asc"))
	private List<Line> lines = new ArrayList<Line>();
	
	@Persistent
	private String notes;

	/**
	 * 
	 */
	public Invoice() {
	}

	/**
	 * @param id
	 * @param date
	 * @param customer
	 */
	public Invoice(String id, Date date, String customer) {
		this.id = id;
		this.date = date;
		this.customer = customer;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the lines
	 */
	public List<Line> getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(List<Line> lines) {
		this.lines = lines;
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

}
