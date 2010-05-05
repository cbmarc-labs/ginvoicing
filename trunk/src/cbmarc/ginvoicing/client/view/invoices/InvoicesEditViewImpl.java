/**
 * 
 */
package cbmarc.ginvoicing.client.view.invoices;

import java.util.List;

import cbmarc.ginvoicing.shared.entity.EntityDisplay;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEditViewImpl extends Composite 
		implements InvoicesEditView {

	@UiTemplate("InvoicesEditView.ui.xml")
	interface uiBinder extends UiBinder<Widget, InvoicesEditViewImpl> {}
	private static uiBinder uiBinder = GWT.create(uiBinder.class);

	@UiField Panel formPanel;
	@UiField ListBox customerList;
	@UiField HasValue<String> notes; 
	@UiField HasWidgets linesPanel;
	
	private Presenter presenter = null;
	
	public InvoicesEditViewImpl() {
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

	@UiHandler("customersReloadButton")
	protected void customersClicked(ClickEvent event) {
		if(presenter != null) {
			presenter.onCustomersReloadButtonClicked();
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
		customerList.setSelectedIndex(0);
		notes.setValue("");
	}

	@Override
	public void focus() {}

	@Override
	public String getCustomer() {
		String cust = null;
		int index = customerList.getSelectedIndex();
		
		try { cust = customerList.getValue(index); } catch(Exception e) {};
		
		return cust;
	}

	@Override
	public void setCustomerList(List<EntityDisplay> items, String selected) {
		int index = 0;
		
		customerList.setEnabled(false);
		customerList.clear();
		for(EntityDisplay item : items) {
			String data[] = item.getData();
			
			customerList.addItem(data[1], data[0]);
			
			if(data[0].equals(selected))
				customerList.setItemSelected(index, true);
			
			index ++;
		}
		
		if(customerList.getItemCount() > 0)
			customerList.setEnabled(true);
	}

	@Override
	public HasWidgets getLinesPanel() {
		return linesPanel;
	}

	@Override
	public HasValue<String> getNotes() {
		return notes;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Panel getFormPanel() {
		return formPanel;
	}
	
}
