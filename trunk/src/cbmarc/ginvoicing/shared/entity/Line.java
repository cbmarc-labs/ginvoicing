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
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable="true")
public class Line extends EntityBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Persistent
	private String quantity = "1";
	
	@Persistent
	private String productId;

	@Persistent
	private String productName;

	@Persistent
	private String productPrice = "0";

	/**
	 * 
	 */
	public Line() {
	}
	
	public Line(String id, String quantity, String productId,
			String productName, String productPrice) {
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
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
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
