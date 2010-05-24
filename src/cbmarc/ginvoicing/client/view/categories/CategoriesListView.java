/**
 * 
 */
package cbmarc.ginvoicing.client.view.categories;

import java.util.List;

import cbmarc.ginvoicing.client.ui.LoadingPanel;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface CategoriesListView {
	
	public interface Presenter {
		void onReloadButtonClicked();
		void onAddButtonClicked();
		void onDeleteButtonClicked(List<Integer> items);
		void onItemClicked(int item);
	}

	LoadingPanel getLoadingPanel();
	Panel getListPanel();
	void setListHeaderLabel(String text);
	void setData(List<EntityDisplay> data);
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
