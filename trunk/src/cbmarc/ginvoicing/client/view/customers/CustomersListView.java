/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CustomersListView {
	
	public interface Presenter {
		void onReloadButtonClicked();
		void onAddButtonClicked();
		void onDeleteButtonClicked(List<Integer> items);
		void onItemClicked(int item);
	}

	Panel getLoadingPanel();
	Panel getListPanel();
	void setListHeaderLabel(String text);
	void setData(List<EntityDisplay> data);
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
