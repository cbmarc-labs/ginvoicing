/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import java.util.List;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.InvoicesEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.InvoicesConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.InvoicesServiceAsync;
import cbmarc.ginvoicing.client.view.invoices.LinesView;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.EntityDisplay;
import cbmarc.ginvoicing.shared.entity.Invoice;
import cbmarc.ginvoicing.shared.entity.Line;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class InvoicesEditPresenter implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getCustomer();
		void setCustomer(List<EntityDisplay> customers, String selected);
		
		Button getCustomerReloadButton();
		
		String getNotes();
		void setNotes(String notes);
		
		HasWidgets getLinesPanel();
		
		public void focus();
		public void reset();
		public void freeze(boolean freezed);
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	private HasWidgets container;
	
	private InvoicesServiceAsync service = InvoicesEventBus.getService();
	private InvoicesConstants constants = InvoicesEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private final LinesPresenter linesPresenter;
	private Invoice invoice = new Invoice();
	
	public InvoicesEditPresenter(Display display) {
	    this.display = display;
	    
	    linesPresenter = new LinesPresenter(new LinesView());
		
		bind();
	}
	
	private void bind() {
		display.addSubmitCancelHandler(this);
		
		display.getCustomerReloadButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				updateCustomerList();
			}
		});
	}
	
	private void updateCustomerList() {
		CustomersEventBus.getService().selectDisplay( 
				new AppAsyncCallback<List<EntityDisplay>>() {
					
					@Override
					public void onSuccess(List<EntityDisplay> result) {
						display.setCustomer(result, invoice.getCustomer());
					}
					
		});
	}
	
	/**
	 * @return
	 */
	protected boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if(!FieldVerifier.isValidString(display.getCustomer())) {
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
		this.container = container;
		container.clear();
		container.add(new Label(constants.loading()));
		
		invoice = new Invoice();
	    linesPresenter.getLinesListPresenter().getList().clear();
	    
	    String[] parts = History.getToken().split("/");
	    if(parts.length > 3)
	    	doLoad(parts[parts.length - 1]);
	    else
	    	updateDisplayFromData();

	    updateCustomerList();
	    //container.add(display.asWidget());
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		List<Line> lines = linesPresenter.getLinesListPresenter().getList();
		
		invoice.setCustomer(display.getCustomer());
		invoice.setNotes(display.getNotes());
		invoice.setLines(lines);
		
		Float amount = 0.0f;
		for(Line l: lines) {
			amount += (l.getQuantity() * l.getPrice());
		}
		
		invoice.setAmount(amount);
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setNotes(invoice.getNotes());
		
		linesPresenter.getLinesListPresenter().setList(invoice.getLines());
		linesPresenter.go(display.getLinesPanel());
		
		container.clear();
		container.add(display.asWidget());
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

}
