/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface ProductsEditView {
	
	public interface Presenter {
		void onListButtonClicked();
		void onSubmitButtonClicked();
		void onCancelButtonClicked();
		void onResetButtonClicked();
	}
	
	HasValue<String> getName();
	HasValue<String> getDescription();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
