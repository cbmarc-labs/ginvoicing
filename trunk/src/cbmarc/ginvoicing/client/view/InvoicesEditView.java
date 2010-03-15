/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.List;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.InvoicesEditPresenter;
import cbmarc.ginvoicing.shared.entity.CustomerDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEditView extends Composite 
		implements InvoicesEditPresenter.Display {
		
	interface uiBinder extends UiBinder<Widget, InvoicesEditView> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);
		
	@UiField ListBox customer;
	@UiField Panel linesPanel;
	
	public InvoicesEditView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		//this.fecha.setFormat(new DateBox.DefaultFormat(
		//		DateTimeFormat.getFormat("dd / MM / yyyy")));
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
		customer.clear();
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
	public String getCustomer() {
		return customer.getValue(customer.getSelectedIndex());
	}

	@Override
	public void setCustomer(List<CustomerDisplay> customers, String selected) {
		int index = 0;
		
		customer.clear();
		for(CustomerDisplay item : customers) {
			customer.addItem(item.getName(), item.getId());
			
			if(item.getId().equals(selected))
				customer.setItemSelected(index, true);
			
			index ++;
		}
	}

	@Override
	public HasWidgets getLinesPanel() {
		return linesPanel;
	}
}