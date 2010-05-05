/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesEditViewImpl extends Composite implements LinesEditView {

	@UiTemplate("LinesEditView.ui.xml")
	interface uiBinder extends UiBinder<Widget, LinesEditViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel formPanel;
	@UiField TextBox quantity;
	@UiField ListBox productList;
	@UiField HasValue<String> price;
	
	private Map<String, EntityDisplay> productDisplayMap = 
        new HashMap<String, EntityDisplay>();
	
	private Presenter presenter = null;
	
	public LinesEditViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@UiHandler("listButton")
	protected void listClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onListButtonClicked();
		}
	}

	@UiHandler("submitButton")
	protected void submitClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onSubmitButtonClicked();
		}
	}

	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onCancelButtonClicked();
		}
	}

	@UiHandler("resetButton")
	protected void resetClicked(ClickEvent event) {
		reset();
		focus();
	}
	
	@UiHandler("productList")
	protected void productListChanged(ChangeEvent event) {
		setPriceFromProductList();
	}

	@UiHandler("productsReloadButton")
	protected void customersClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onProductsReloadButtonClicked();
		}
	}

	@Override
	public void reset() {
		quantity.setValue("1.0");
		productList.setSelectedIndex(0);
		price.setValue("0.0");

		setPriceFromProductList();
	}

	@Override
	public void focus() {
		quantity.setFocus(true);
	}

	@Override
	public HasValue<String> getQuantity() {
		return quantity;
	}

	@Override
	public void setProductList(List<EntityDisplay> items, String selected) {
		int index = 0;
		
		productList.setEnabled(false);
		productList.clear();
		productDisplayMap.clear();
		for(EntityDisplay item : items) {
			String data[] = item.getData();
			
			productList.addItem(data[1], data[0]);
			productDisplayMap.put(data[0], item);
			
			if(data[0].equals(selected))
				productList.setItemSelected(index, true);
			
			index ++;
		}
		
		if(productList.getItemCount() > 0)
			productList.setEnabled(true);
		
		setPriceFromProductList();
	}
	
	/**
	 * Set field price value from list product
	 */
	public void setPriceFromProductList() {
		if(productList.getItemCount() > 0) {
			EntityDisplay item = getProduct();
			
			price.setValue(item.getData()[4]);
		}
	}

	/**
	 * @return the productPrice
	 */
	public HasValue<String> getPrice() {
		return price;
	}

	@Override
	public EntityDisplay getProduct() {
		try {
            int index = productList.getSelectedIndex();
            String prod = productList.getValue(index);
            
            return productDisplayMap.get(prod);
		} catch(Exception e) {}
    
    	return null;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Panel getFormPanel() {
		return formPanel;
	}
	
}
