/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface MainView {
	
	public interface Presenter {
	}

	void setActiveTab(Integer item);
	
	HasWidgets getContentPanel();
	Widget asWidget();
}
