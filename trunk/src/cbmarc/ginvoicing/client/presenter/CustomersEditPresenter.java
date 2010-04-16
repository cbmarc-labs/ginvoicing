/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.event.SubmitCancelEvent;
import cbmarc.ginvoicing.client.event.SubmitCancelHandler;
import cbmarc.ginvoicing.client.i18n.AppMessages;
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
		
		String getContact();
		void setContact(String value);
		
		String getAddress();
		void setAddress(String value);
		
		String getCity();
		void setCity(String value);
		
		String getCountry();
		void setCountry(String value);
		
		String getPhone();
		void setPhone(String value);
		
		String getFax();
		void setFax(String value);
		
		String getEmail();
		void setEmail(String value);
		
		String getNotes();
		void setNotes(String value);
		
		public void focus();
		public void reset();
		
		public HandlerRegistration addSubmitCancelHandler(
				SubmitCancelHandler handler);
		Widget asWidget();
	}
	
	private final Display display;
	
	private CustomersServiceAsync service = CustomersEventBus.getService();
	private AppMessages messages = EventBus.getMessages();
	
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
		StringBuilder sb = new StringBuilder();
		
		if (!FieldVerifier.isValidString(display.getName())) {
			sb.append(messages.errorField("Name") + "\n");
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
		
		service.save(customer, new AppAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
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
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		customer = new Customer();
		
	    String[] parts = History.getToken().split("/");
	    if(parts.length > 3)
	    	doLoad(parts[parts.length - 1]);
	    
		updateDisplayFromData();
		container.add(display.asWidget());
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		customer.setName(display.getName());
		customer.setContact(display.getContact());
		customer.setAddress(display.getAddress());
		customer.setCity(display.getCity());
		customer.setCountry(display.getCountry());
		customer.setPhone(display.getPhone());
		customer.setFax(display.getFax());
		customer.setEmail(display.getEmail());
		customer.setNotes(display.getNotes());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		display.reset();
		display.setName(customer.getName());
		display.setContact(customer.getContact());
		display.setAddress(customer.getAddress());
		display.setCity(customer.getCity());
		display.setCountry(customer.getCountry());
		display.setPhone(customer.getPhone());
		display.setFax(customer.getFax());
		display.setEmail(customer.getEmail());
		display.setNotes(customer.getNotes());
		display.focus();
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
