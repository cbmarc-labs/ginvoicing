/**
 * 
 */
package cbmarc.ginvoicing.client.view.customers;

import cbmarc.ginvoicing.client.ui.LoadingPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersEditViewImpl extends Composite 
		implements CustomersEditView {

	@UiTemplate("CustomersEditView.ui.xml")
	interface uiBinder extends UiBinder<Widget, CustomersEditViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField LoadingPanel loadingPanel;
	@UiField Panel formPanel;
	@UiField TextBox name;
	@UiField HasValue<String> contact;
	@UiField HasValue<String> address;
	@UiField HasValue<String> city;
	@UiField HasValue<String> country;
	@UiField HasValue<String> phone;
	@UiField HasValue<String> fax;
	@UiField HasValue<String> email;
	@UiField HasValue<String> notes;
	
	private Presenter presenter = null;
	
	public CustomersEditViewImpl() {
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

	@Override
	public void reset() {
		name.setValue("");
		contact.setValue("");
		address.setValue("");
		city.setValue("");
		country.setValue("");
		phone.setValue("");
		fax.setValue("");
		email.setValue("");
		notes.setValue("");
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}

	@Override
	public HasValue<String> getAddress() {
		return address;
	}

	@Override
	public HasValue<String> getCity() {
		return city;
	}

	@Override
	public HasValue<String> getContact() {
		return contact;
	}

	@Override
	public HasValue<String> getCountry() {
		return country;
	}

	@Override
	public HasValue<String> getEmail() {
		return email;
	}

	@Override
	public HasValue<String> getFax() {
		return fax;
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}

	@Override
	public HasValue<String> getNotes() {
		return notes;
	}

	@Override
	public HasValue<String> getPhone() {
		return phone;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Panel getFormPanel() {
		return formPanel;
	}

	@Override
	public LoadingPanel getLoadingPanel() {
		return loadingPanel;
	}
	
}
