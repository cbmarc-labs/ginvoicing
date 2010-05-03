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
	
	private final LinesPresenter linesPresenter = new LinesPresenter(new LinesViewImpl());
	private Invoice invoice = new Invoice();
	
	public InvoicesEditPresenter(InvoicesEditView view) {
	    this.view = view;
	    view.setPresenter(this);
	}
	
	private void updateCustomerList() {
		CustomersEventBus.getService().selectDisplay( 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						view.setCustomerList(result, invoice.getCustomer());
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
	public void doSave() {
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
		service.selectById(id, new AppAsyncCallback<Invoice>() {

			@Override
			public void onSuccess(Invoice result) {				
				invoice = result;
				updateDisplayFromData();
			}
			
		});
	}
	
	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		invoice = new Invoice();
	    linesPresenter.getLinesListPresenter().getList().clear();
	    
	    String[] parts = History.getToken().split("/");
	    if(parts.length > 3)
	    	doLoad(parts[parts.length - 1]);
	    else
	    	updateDisplayFromData();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		List<Line> lines = linesPresenter.getLinesListPresenter().getList();
		
		invoice.setCustomer(view.getCustomer());
		invoice.setNotes(view.getNotes().getValue());
		invoice.setLines(lines);
		
		Float amount = 0.0f;
		for(Line l: lines) amount += (l.getQuantity() * l.getPrice());
		
		invoice.setAmount(amount);
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		view.reset();
		
		updateCustomerList();
		view.getNotes().setValue(invoice.getNotes());
		
		linesPresenter.getLinesListPresenter().setList(invoice.getLines());
		linesPresenter.go(view.getLinesPanel());
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
