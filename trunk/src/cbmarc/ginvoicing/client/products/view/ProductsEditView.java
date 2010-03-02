/**
 * 
 */
package cbmarc.ginvoicing.client.products.view;

import cbmarc.ginvoicing.client.products.event.ProductsEditEvent;
import cbmarc.ginvoicing.client.products.event.ProductsEditHandler;
import cbmarc.ginvoicing.client.products.presenter.ProductsEditPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class ProductsEditView extends Composite 
		implements ProductsEditPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, ProductsEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField TextBox name;
	@UiField HasValue<String> description;
	@UiField ListBox category;
	@UiField HasValue<String> price;
	
	public ProductsEditView() {
		initWidget(uiBinder.createAndBindUi(this));
		category.addItem("uno", "1");
		category.addItem("dos", "2");
	}
	
	public Widget asWidget() {
		return this;
	}

	@UiHandler("listButton")
	protected void listClicked(ClickEvent event) {
		fireEvent(ProductsEditEvent.list());
	}

	@UiHandler("submitButton")
	protected void submitClicked(ClickEvent event) {
		fireEvent(ProductsEditEvent.submit());
	}

	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		fireEvent(ProductsEditEvent.cancel());
	}

	@Override
	public void reset() {
		name.setValue("");
		description.setValue("");
	}

	@Override
	public String getName() {
		return this.name.getValue();
	}

	@Override
	public String getDescription() {
		return description.getValue();
	}

	@Override
	public HandlerRegistration addHandler(ProductsEditHandler handler) {
		return addHandler(handler, ProductsEditEvent.getType());
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}

	@Override
	public void setDescription(String value) {
		description.setValue(value);
	}

	@Override
	public void setName(String value) {
		name.setValue(value);
	}
}
