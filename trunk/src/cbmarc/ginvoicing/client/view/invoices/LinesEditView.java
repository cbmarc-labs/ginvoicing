/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public interface LinesEditView {
	
	public interface Presenter {
		void onListButtonClicked();
		void onSubmitButtonClicked();
		void onCancelButtonClicked();
		
		void onProductsReloadButtonClicked();
	}
	
	HasValue<String> getQuantity();
	EntityDisplay getProduct();
	void setProductList(List<EntityDisplay> items, String selected);
	HasValue<String> getPrice();
	
	void focus();
	void reset();
	
	void setPresenter(Presenter presenter);
	Widget asWidget();
}
