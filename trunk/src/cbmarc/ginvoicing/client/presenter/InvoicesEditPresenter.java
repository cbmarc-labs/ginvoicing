/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;
import cbmarc.ginvoicing.client.view.LinesView;
import cbmarc.ginvoicing.shared.entity.CustomerDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEditPresenter implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getCustomer();
		void setCustomer(List<CustomerDisplay> customers, String selected);
		
		HasWidgets getLinesPanel();
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private InvoicesServiceAsync service = InvoicesEventBus.getService();
	private Invoice invoice = new Invoice();
	
	private final LinesPresenter linesPresenter;
	
	public InvoicesEditPresenter(Display view) {
	    this.display = view;
	    
	    linesPresenter = new LinesPresenter(new LinesView());
		
		bind();
	}
	
	private void bind() {
		display.addSubmitCancelHandler(this);
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		
		return valid;
	}

	/**
	 * @return
	 */
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(invoice, new AppAsyncCallback<Invoice>() {

			@Override
			public void onSuccess(Invoice result) {
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
				linesPresenter.go(display.getLinesPanel());
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	    
	    linesPresenter.getLinesListPresenter().getList().clear();
	    
	    // TODO: fix up
	    String[] parts = History.getToken().split("/");
	    if(parts.length > 3) {
	    	doLoad(parts[parts.length - 1]);
	    } else {
	    	invoice = new Invoice();
	    	updateDisplayFromData();
	    	linesPresenter.go(display.getLinesPanel());
	    }
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		invoice.setCustomer(display.getCustomer());
		invoice.setLines(linesPresenter.getLinesListPresenter().getList());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		
		CustomersEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<CustomerDisplay>>() {
					
					@Override
					public void onSuccess(List<CustomerDisplay> result) {
						display.setCustomer(result, invoice.getCustomer());
					}
					
		});
		
		linesPresenter.getLinesListPresenter().setList(invoice.getLines());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		History.newItem("main/invoices");
	}

	@Override
	public void onSubmit(SubmitCancelEvent event) {
		if(hasValidInput())
			doSave();
	}

	@Override
	public void processHistoryToken(String token) {
		// Nothing to do.
	}

}
