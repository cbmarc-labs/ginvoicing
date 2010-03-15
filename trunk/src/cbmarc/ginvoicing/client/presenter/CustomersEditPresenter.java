/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CustomersServiceAsync;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Customer;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersEditPresenter implements Presenter, SubmitCancelHandler {
	
	public interface Display {
		String getName();
		void setName(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private CustomersServiceAsync service = CustomersEventBus.getService();
	private Customer customer = new Customer();
	
	public CustomersEditPresenter(Display view) {
	    this.display = view;
		
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
		
		// TODO validate all fields
		// First, we validate the input.
		if (!FieldVerifier.isValidName(display.getName())) {
			Window.alert("Please enter at least four characters on name field.");
			
			return false;
		}
		
		return valid;
	}

	/**
	 * @return
	 */
	public void doSave() {
		updateDataFromDisplay();
		
		service.save(customer, new AppAsyncCallback<Customer>() {

			@Override
			public void onSuccess(Customer result) {
				History.newItem("main/customers");
			}
			
		});
	}
	
	/**
	 * 
	 */
	private void doLoad(String id) {
		service.selectById(id, new AppAsyncCallback<Customer>() {

			@Override
			public void onSuccess(Customer result) {
				customer = result;
				updateDisplayFromData();
				display.focus();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
	    container.add(display.asWidget());
	    
	    // TODO: fix up this shit
	    String token = History.getToken();
	    String[] parts = token.split("/");
	    if(parts.length > 3) doLoad(parts[parts.length - 1]);
	    else customer = new Customer();

		updateDisplayFromData();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		customer.setName(display.getName());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setName(customer.getName());
	}

	@Override
	public void onCancel(SubmitCancelEvent event) {
		History.newItem("main/customers");
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