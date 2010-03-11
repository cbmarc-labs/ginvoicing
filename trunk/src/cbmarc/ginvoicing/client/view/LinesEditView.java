/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.LinesEditPresenter;
import cbmarc.ginvoicing.shared.entity.ProductDisplay;

import com.google.gwt.core.client.GWT;
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
	@UiField ListBox product;
	
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

	@Override
	public void reset() {
		quantity.setValue("");
		product.clear();
	}

	@Override
	public HandlerRegistration addSubmitCancelHandler(
			SubmitCancelHandler handler) {
		return addHandler(handler, SubmitCancelEvent.getType());
	}

	@Override
	public void focus() {
		//name.setFocus(true);
	}

	@Override
	public Integer getQuantity() {
		return Integer.parseInt(quantity.getValue());
	}

	@Override
	public void setQuantity(Integer value) {
		quantity.setValue(value.toString());
	}

	@Override
	public String getProduct() {
		return product.getValue(product.getSelectedIndex());
	}

	@Override
	public void setProduct(List<ProductDisplay> products, String selected) {
		int index = 0;
		
		product.clear();
		for(ProductDisplay item : products) {
			product.addItem(item.getName(), item.getId());
			
			if(item.getId().equals(selected))
				product.setItemSelected(index, true);
			
			index ++;
		}
	}
}
