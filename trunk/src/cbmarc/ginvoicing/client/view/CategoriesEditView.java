/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.CategoriesEditPresenter;

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

	@Override
	public void reset() {
		name.setValue("");
		description.setValue("");
	}

	/* (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.categories.presenter.CategoriesEditPresenter.Display#addHandler(cbmarc.ginvoicing.client.categories.event.CategoriesEditHandler)
	 */
	@Override
	public HandlerRegistration addSubmitCancelHandler(
			SubmitCancelHandler handler) {
		return addHandler(handler, SubmitCancelEvent.getType());
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}

	@Override
	public String getDescription() {
		return description.getValue();
	}

	@Override
	public String getName() {
		return name.getValue();
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
