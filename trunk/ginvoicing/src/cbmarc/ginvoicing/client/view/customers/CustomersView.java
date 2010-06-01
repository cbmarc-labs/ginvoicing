/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CustomersView {
	
	public interface Presenter {
	}
	
	HasWidgets getContent();
	Widget asWidget();
}
