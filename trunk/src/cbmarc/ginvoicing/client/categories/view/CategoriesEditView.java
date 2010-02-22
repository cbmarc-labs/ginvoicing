/**
 * 
 */
package cbmarc.ginvoicing.client.categories.view;

import cbmarc.ginvoicing.client.categories.event.CategoriesEditEvent;
import cbmarc.ginvoicing.client.categories.event.CategoriesEditHandler;
import cbmarc.ginvoicing.client.categories.presenter.CategoriesEditPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CategoriesEditView extends Composite 
		implements CategoriesEditPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, CategoriesEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField TextBox name;
	@UiField HasValue<String> description;
	
	public CategoriesEditView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget asWidget() {
		  return this;
	}

	@UiHandler("listButton")
	protected void listClicked(ClickEvent event) {
		fireEvent(CategoriesEditEvent.list());
	}

	@UiHandler("submitButton")
	protected void submitClicked(ClickEvent event) {
		fireEvent(CategoriesEditEvent.submit());
	}

	@UiHandler("cancelButton")
	protected void cancelClicked(ClickEvent event) {
		fireEvent(CategoriesEditEvent.cancel());
	}

	@Override
	public void reset() {
		this.name.setValue("");
		this.description.setValue("");
	}

	@Override
	public TextBox getName() {
		return this.name;
	}

	/**
	 * @return the nombre
	 */
	public final HasValue<String> getDescription() {
		return description;
	}

	@Override
	public HandlerRegistration addHandler(CategoriesEditHandler handler) {
		return addHandler(handler, CategoriesEditEvent.getType());
	}
}
