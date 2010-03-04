/**
 * 
 */
package cbmarc.ginvoicing.client.ui;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author MCOSTA
 *
 */
public interface HomeResources extends ClientBundle {
	@Source("FOOOD's Icons.jpg")
	ImageResource categories();
	
	@Source("My Icons.jpg")
	ImageResource products();
	
	@Source("Users 2.jpg")
	ImageResource customers();
	
	@Source("Email.jpg")
	ImageResource invoices();
}
