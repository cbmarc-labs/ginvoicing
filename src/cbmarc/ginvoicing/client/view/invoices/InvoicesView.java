/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface InvoicesView {
	
	public interface Presenter {
	}
	
	HasWidgets getContent();
	Widget asWidget();
}
