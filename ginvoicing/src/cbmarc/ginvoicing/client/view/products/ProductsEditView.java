/**
 * 
 */
package cbmarc.ginvoicing.client.view.products;

import java.util.List;

import cbmarc.ginvoicing.client.ui.LoadingPanel;
import cbmarc.ginvoicing.shared.entity.Category;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
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
		
		void onCategoriesReloadButtonClicked();
	}

	LoadingPanel getLoadingPanel();
	Panel getFormPanel();
	HasValue<String> getName();
	HasValue<String> getDescription();
	String getCategory();
	ListBox getCategoryList();
	void setCategoryList(List<Category> items, String selected);
	HasValue<String> getPrice();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
