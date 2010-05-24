/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import cbmarc.ginvoicing.client.ui.LoadingPanel;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CategoriesEditView {
	
	public interface Presenter {
		void onListButtonClicked();
		void onSubmitButtonClicked();
		void onCancelButtonClicked();
	}
	
	LoadingPanel getLoadingPanel();
	Panel getFormPanel();
	HasValue<String> getName();
	HasValue<String> getDescription();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
