/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.LinesEditPresenter;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class LinesEditView extends Composite 
		implements LinesEditPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, LinesEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField TextBox quantity;
	@UiField ListBox productList;
	@UiField TextBox productPrice;
	
	private Map<String, EntityDisplay> productDisplayMap = 
		new HashMap<String, EntityDisplay>();
	
	public LinesEditView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@UiHandler("listButton")
	protected void listClicked(ClickEvent event) {
		fireEvent(SubmitCancelEvent.cancel());
	}

	@UiHandler("submitButton")
	protected void submitClicked(ClickEvent event) {
		fireEvent(SubmitCancelEvent.submit());
	}

	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		fireEvent(SubmitCancelEvent.cancel());
	}

	@UiHandler("resetButton")
	protected void resetClicked(ClickEvent event) {
		reset();
		focus();
	}
	
	@UiHandler("productList")
	protected void productListClicked(ChangeEvent event) {
		setProductPriceFromProductList();
	}

	@Override
	public void reset() {
		quantity.setValue("1");
		productList.setSelectedIndex(0);
		productPrice.setValue("0.0");

		setProductPriceFromProductList();
	}

	@Override
	public HandlerRegistration addSubmitCancelHandler(
			SubmitCancelHandler handler) {
		return addHandler(handler, SubmitCancelEvent.getType());
	}

	@Override
	public void focus() {
		quantity.setFocus(true);
	}

	@Override
	public String getQuantity() {
		return quantity.getValue();
	}

	@Override
	public void setQuantity(String value) {
		quantity.setValue(value);
	}

	@Override
	public void setProduct(List<EntityDisplay> list, String selected) {
		int index = 0;
		
		productList.clear();
		productDisplayMap.clear();
		for(EntityDisplay item : list) {
			String data[] = item.getData();
			
			productList.addItem(data[1], data[0]);
			productDisplayMap.put(data[0], item);
			
			if(data[0].equals(selected))
				productList.setItemSelected(index, true);
			
			index ++;
		}
		
		setProductPriceFromProductList();
	}
	
	/**
	 * Set field price value from list product
	 */
	private void setProductPriceFromProductList() {
		if(productList.getItemCount() > 0) {
			EntityDisplay obj = getProduct();
			productPrice.setValue(obj.getData()[4]);
		}
	}

	/**
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice.getValue();
	}

	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(String value) {
		productPrice.setValue(value);
	}

	@Override
	public EntityDisplay getProduct() {
		String selected = productList.getValue(productList.getSelectedIndex());
		
		return productDisplayMap.get(selected);
	}
	
}
