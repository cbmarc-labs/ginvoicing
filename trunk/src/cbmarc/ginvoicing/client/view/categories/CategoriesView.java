/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CategoriesView {
	
	public interface Presenter {
	}
	
	HasWidgets getContent();
	
	Widget asWidget();
}
