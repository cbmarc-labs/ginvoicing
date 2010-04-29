/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface ProductsListView {
	
	public interface Presenter {
		void onReloadButtonClicked();
		void onAddButtonClicked();
		void onDeleteButtonClicked(List<Integer> rows);
		void onItemClicked(int row);
	}
	
	void setListHeaderLabel(String text);
	void setData(List<EntityDisplay> data);
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
