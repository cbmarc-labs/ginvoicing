/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CustomersEditView {
	
	public interface Presenter {
		void onListButtonClicked();
		void onSubmitButtonClicked();
		void onCancelButtonClicked();
	}
	
	HasValue<String> getName();
	HasValue<String> getContact();
	HasValue<String> getAddress();
	HasValue<String> getCity();
	HasValue<String> getCountry();
	HasValue<String> getPhone();
	HasValue<String> getFax();
	HasValue<String> getEmail();
	HasValue<String> getNotes();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
