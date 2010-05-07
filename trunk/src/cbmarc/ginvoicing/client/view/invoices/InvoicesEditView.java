/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.List;

import cbmarc.ginvoicing.client.ui.LoadingPanel;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface InvoicesEditView {
	
	public interface Presenter {
		void onListButtonClicked();
		void onSubmitButtonClicked();
		void onCancelButtonClicked();

		void onCustomersReloadButtonClicked();
	}

	LoadingPanel getLoadingPanel();
	Panel getFormPanel();
	String getCustomer();
	void setCustomerList(List<EntityDisplay> items, String selected);
	ListBox getCustomerList();
	HasValue<String> getNotes();
	HasWidgets getLinesPanel();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
