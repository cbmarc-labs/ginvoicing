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
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;

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
		void setCustomer(List<EntityDisplay> customers, String selected);
		
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
				linesPresenter.go(display.getLinesPanel());
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
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
	    
		container.clear();
		container.add(display.asWidget());
		display.focus();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		List<Line> lines = linesPresenter.getLinesListPresenter().getList();
		
		invoice.setCustomer(display.getCustomer());
		invoice.setLines(lines);
		
		// TODO check this
		/*Integer amount = 0;
		for(Line l: lines) {
			if(!l.getProductPrice().isEmpty())
				amount = amount + (Integer.parseInt(l.getProductPrice()) *
						Integer.parseInt(l.getQuantity()));
		}
		
		invoice.setAmount(amount.toString());*/
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		
		CustomersEventBus.getService().selectDisplay(null, 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
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
