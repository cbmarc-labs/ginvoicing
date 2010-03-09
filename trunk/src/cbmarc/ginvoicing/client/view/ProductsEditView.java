/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.ProductsEditPresenter;
import cbmarc.ginvoicing.shared.entity.CategoryDisplay;

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
		name.setValue("");
		description.setValue("");
		category.clear();
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
	public HandlerRegistration addSubmitCancelHandler(
			SubmitCancelHandler handler) {
		return addHandler(handler, SubmitCancelEvent.getType());
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category.getValue(category.getSelectedIndex());
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(List<CategoryDisplay> categories, String selected) {
		int index = 0;
		
		category.clear();
		for(CategoryDisplay item : categories) {
			category.addItem(item.getDescription(), item.getId());
			
			if(item.getId().equals(selected))
				category.setItemSelected(index, true);
			
			index ++;
		}
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

	@Override
	public String getPrice() {
		return price.getValue();
	}

	@Override
	public void setPrice(String value) {
		price.setValue(value);
	}
}
