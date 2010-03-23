/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION) //, detachable="true")
public class Invoice extends EntityBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// date invoice created
	@Persistent
	private Date date;
	
	// foreign key to customer
	@Persistent
	private String customerId;
	
	@Persistent
	private String customerName;
	
	//@Persistent(serialized="true", defaultFetchGroup="true")
	//@Order(extensions = @Extension(vendorName="datanucleus", key="list-ordering", value="quantity asc"))
	//@Persistent(mappedBy = "invoice")
	@Element(dependent = "true")
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
	 * @param date
	 * @param customerId
	 * @param customerName
	 * @param lines
	 * @param notes
	 * @param amount
	 */
	public Invoice(Date date, String customerId, String customerName,
			List<Line> lines, String notes, String amount) {
		this.date = date;
		this.customerId = customerId;
		this.customerName = customerName;
		this.lines = lines;
		this.notes = notes;
		this.amount = amount;
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
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
