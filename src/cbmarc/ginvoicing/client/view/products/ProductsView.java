/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface ProductsView {
	
	public interface Presenter {
	}
	
	HasWidgets getContent();
	Widget asWidget();
}
