/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;
import cbmarc.ginvoicing.client.view.invoices.InvoicesEditView;
import cbmarc.ginvoicing.client.view.invoices.LinesViewImpl;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEditPresenter 
		implements Presenter, InvoicesEditView.Presenter {
		
	private final InvoicesEditView view;
	
	private InvoicesServiceAsync service = InvoicesEventBus.getService();
	private InvoicesConstants constants = InvoicesEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private static LinesViewImpl linesView = null;

	private Invoice invoice;
	
	public InvoicesEditPresenter(InvoicesEditView view, String id) {
	    this.view = view;
	    view.setPresenter(this);
	    
	    invoice = new Invoice();
	    if(id != null) doLoad(id);
    		else updateDisplayFromData();
	}
	
	private void updateCustomerList() {
		view.getCustomerList().setEnabled(false);
		CustomersEventBus.getService().selectDisplay( 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						if(!result.isEmpty()) {
							view.getCustomerList().setEnabled(true);
							view.setCustomerList(result, invoice.getCustomer());
						}
					}
					
		});
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if(!FieldVerifier.isValidString(view.getCustomer())) {
			sb.append(messages.errorField(constants.formCustomerName()) + "\n");
			valid = false;
		}
		
		sb.append("   ");
		if(valid == false)
			Window.alert(sb.toString());
		
		return valid;
	}

	/**
	 * @return
	 */
	private void doSave() {
		updateDataFromDisplay();
		service.save(invoice, new AppAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				History.newItem("main/invoices");
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doLoad(String id) {	
		view.getFormPanel().setVisible(false);	
		view.getLoadingPanel().setVisible(true);
		service.selectById(id, new AppAsyncCallback<Invoice>() {

			@Override
			public void onSuccess(Invoice result) {				
				invoice = result;
				view.getFormPanel().setVisible(true);
				view.getLoadingPanel().setVisible(false);
				updateDisplayFromData();
			}
			
		});
	}
	
	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		view.focus();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		invoice.setCustomer(view.getCustomer());
		invoice.setNotes(view.getNotes().getValue());
		
		Float amount = 0.0f;
		for(Line l: invoice.getLines())
			amount += (l.getQuantity() * l.getPrice());
		
		invoice.setAmount(amount);
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		view.reset();
		updateCustomerList();
		view.getNotes().setValue(invoice.getNotes());
		
		if(linesView == null)
			linesView = new LinesViewImpl();
		
		new LinesPresenter(linesView, invoice.getLines()).
			go(view.getLinesPanel());
		
		view.focus();
	}

	@Override
	public void onCancelButtonClicked() {
		History.newItem("main/invoices");
	}

	@Override
	public void onCustomersReloadButtonClicked() {
		updateCustomerList();
	}

	@Override
	public void onListButtonClicked() {
		History.newItem("main/invoices");
	}

	@Override
	public void onSubmitButtonClicked() {
		if(hasValidInput()) doSave();
	}

}
