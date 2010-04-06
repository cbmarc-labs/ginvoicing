/**
 * 
 */
package cbmarc.ginvoicing.shared.entity;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * @author MCOSTA
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Line extends EntityBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private String product;
	
	// Field for display purposes only
	@NotPersistent
	private String productName;
	
	@Persistent
	private String quantity = "1";

	@Persistent
	private String price = null;

	/**
	 * 
	 */
	public Line() {
	}

	/**
	 * @param id
	 * @param product
	 * @param productName
	 * @param quantity
	 * @param productPrice
	 */
	public Line(String id, String product, String productName, 
			String quantity, String price) {
		this.id = id;
		this.product = product;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
}
