/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Invoice extends EntityBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private Date date;
	
	@Persistent
	private String customer;
	
	//@Persistent(serialized="true", defaultFetchGroup="true")
	//@Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="quantity asc"))
	//@Persistent(mappedBy = "invoice")
	//@Element(dependent = "true")
	private List<Line> lines = new ArrayList<Line>();
	
	@Persistent
	private String notes;
	
	@Persistent
	private String amount;

	/**
	 * 
	 */
	public Invoice() {
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

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
