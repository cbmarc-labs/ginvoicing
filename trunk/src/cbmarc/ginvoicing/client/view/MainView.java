/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface MainView {
	
	public interface Presenter {
	}
	
	List<Hyperlink> getMenuTab();
	void setActiveTab(Hyperlink hyperlink);

	HasWidgets getContent();
	Widget asWidget();
}
