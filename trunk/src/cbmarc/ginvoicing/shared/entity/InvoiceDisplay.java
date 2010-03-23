/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MCOSTA
 *
 */
@SuppressWarnings("serial")
public class InvoiceDisplay implements Serializable {
	private String id;
	private String customerName;
	private Date date;
	private String amount;
	
	/**
	 * 
	 */
	public InvoiceDisplay() {
	}

	/**
	 * @param id
	 * @param customerName
	 * @param date
	 * @param amount
	 */
	public InvoiceDisplay(String id, String customerName, Date date,
			String amount) {
		this.id = id;
		this.customerName = customerName;
		this.date = date;
		this.amount = amount;
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
