/**
 * 
 */
package cbmarc.ginvoicing.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.presenter.InvoicesEditPresenter;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;

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
import com.google.gwt.user.client.ui.TextArea;
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
	@UiField TextArea notes; 
	@UiField Panel linesPanel;

	private Map<String, EntityDisplay> customerDisplayMap = 
		new HashMap<String, EntityDisplay>();
	
	public InvoicesEditView() {
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
		customer.setSelectedIndex(0);
		notes.setValue("");
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
	public void setCustomer(List<EntityDisplay> customers, String selected) {
		int index = 0;
		
		customer.clear();
		customerDisplayMap.clear();
		for(EntityDisplay item : customers) {
			String data[] = item.getData();
			
			customer.addItem(data[1], data[0]);
			customerDisplayMap.put(data[0], item);
			
			if(data[0].equals(selected))
				customer.setItemSelected(index, true);
			
			index ++;
		}
	}

	@Override
	public HasWidgets getLinesPanel() {
		return linesPanel;
	}

	@Override
	public String getNotes() {
		return notes.getValue();
	}

	@Override
	public void setNotes(String value) {
		notes.setValue(value);
	}
}
