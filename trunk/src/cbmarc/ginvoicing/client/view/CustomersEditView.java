/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.CustomersEditPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersEditView extends Composite 
		implements CustomersEditPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, CustomersEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField TextBox name;
	@UiField TextBox contact;
	@UiField TextBox address;
	@UiField TextBox city;
	@UiField TextBox country;
	@UiField TextBox phone;
	@UiField TextBox fax;
	@UiField TextBox email;
	@UiField TextArea notes;
	
	public CustomersEditView() {
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
	public HandlerRegistration addSubmitCancelHandler(
			SubmitCancelHandler handler) {
		return addHandler(handler, SubmitCancelEvent.getType());
	}

	@Override
	public void focus() {
		name.setFocus(true);
	}
	
	/*
	 * GETTERS AND SETTERS ****************************************************
	 */
	
	/*
	 * (non-Javadoc)
	 * @see cbmarc.ginvoicing.client.presenter.CustomersEditPresenter.Display#getName()
	 */
	@Override
	public String getName() {
		return name.getValue();
	}

	@Override
	public void setName(String value) {
		name.setValue(value);
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact.getValue();
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String value) {
		contact.setValue(value);
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address.getValue();
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String value) {
		address.setValue(value);
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city.getValue();
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String value) {
		city.setValue(value);
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country.getValue();
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String value) {
		country.setValue(value);
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone.getValue();
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String value) {
		phone.setValue(value);
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax.getValue();
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String value) {
		fax.setValue(value);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email.getValue();
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String value) {
		email.setValue(value);
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes.getValue();
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String value) {
		notes.setValue(value);
	}
	
}
