/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.event.CustomersEventBus;
import cbmarc.ginvoicing.client.event.EventBus;
import cbmarc.ginvoicing.client.i18n.AppMessages;
import cbmarc.ginvoicing.client.i18n.CustomersConstants;
import cbmarc.ginvoicing.client.rpc.AppAsyncCallback;
import cbmarc.ginvoicing.client.rpc.CustomersServiceAsync;
import cbmarc.ginvoicing.client.view.customers.CustomersEditView;
import cbmarc.ginvoicing.shared.FieldVerifier;
import cbmarc.ginvoicing.shared.entity.Customer;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class CustomersEditPresenter 
		implements Presenter, CustomersEditView.Presenter {
	
	private final CustomersEditView view;
	
	private CustomersServiceAsync service = CustomersEventBus.getService();
	private CustomersConstants constants = CustomersEventBus.getConstants();
	private AppMessages messages = EventBus.getMessages();
	
	private Customer customer;
	
	public CustomersEditPresenter(CustomersEditView view, String id) {
	    this.view = view;
	    view.setPresenter(this);
	    
	    customer = new Customer();
	    if(id != null) doLoad(id);
	    	else updateDisplayFromData();
	}
	
	/**
	 * @return
	 */
	public boolean hasValidInput() {
		boolean valid = true;
		StringBuilder sb = new StringBuilder();
		
		if (!FieldVerifier.isValidString(view.getName().getValue())) {
			sb.append(messages.errorField(constants.formName()) + "\n");
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
		view.getFormPanel().setVisible(false);
		view.getLoadingPanel().setVisible(true);
		service.selectById(id, new AppAsyncCallback<Customer>() {

			@Override
			public void onSuccess(Customer result) {
				customer = result;
				view.getFormPanel().setVisible(true);
				view.getLoadingPanel().setVisible(false);
				updateDisplayFromData();
			}
			
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		view.focus();
	}
	
	/**
	 * 
	 */
	public void updateDataFromDisplay() {
		customer.setName(view.getName().getValue());
		customer.setContact(view.getContact().getValue());
		customer.setAddress(view.getAddress().getValue());
		customer.setCity(view.getCity().getValue());
		customer.setCountry(view.getCountry().getValue());
		customer.setPhone(view.getPhone().getValue());
		customer.setFax(view.getFax().getValue());
		customer.setEmail(view.getEmail().getValue());
		customer.setNotes(view.getNotes().getValue());
	}
	
	/**
	 * 
	 */
	public void updateDisplayFromData() {
		view.reset();
		view.getName().setValue(customer.getName());
		view.getContact().setValue(customer.getContact());
		view.getAddress().setValue(customer.getAddress());
		view.getCity().setValue(customer.getCity());
		view.getCountry().setValue(customer.getCountry());
		view.getPhone().setValue(customer.getPhone());
		view.getFax().setValue(customer.getFax());
		view.getEmail().setValue(customer.getEmail());
		view.getNotes().setValue(customer.getNotes());
		view.focus();
	}

	@Override
	public void onCancelButtonClicked() {
		History.newItem("main/customers");
	}

	@Override
	public void onListButtonClicked() {
		History.newItem("main/customers");
	}

	@Override
	public void onSubmitButtonClicked() {
		if(hasValidInput()) doSave();
	}

}
