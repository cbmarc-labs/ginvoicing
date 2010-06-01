/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.customers.CustomersEditViewImpl;
import cbmarc.ginvoicing.client.view.customers.CustomersListViewImpl;
import cbmarc.ginvoicing.client.view.customers.CustomersView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author MCOSTA
 *
 */
public class CustomersPresenter implements Presenter, CustomersView.Presenter {
	
	private static final String VIEW = MainPresenter.VIEW_CUSTOMERS;

	private static CustomersListViewImpl customersListView = null;
	private static CustomersEditViewImpl customersEditView = null;
		
	private final CustomersView view;
	
	/**
	 * @param view
	 */
	public CustomersPresenter(CustomersView view) {
	    this.view = view;
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			if (token.startsWith(VIEW + "/edit")) {
				String[] parts = token.split("/");
				String id = null; 
				
				if(customersEditView == null)
					customersEditView = new CustomersEditViewImpl();
				
				// is a edit statement?
				if(parts.length > 3) id = parts[parts.length - 1];
				
				new CustomersEditPresenter(customersEditView, id).
					go(view.getContent());
			} else {
				if(customersListView == null)
					customersListView = new CustomersListViewImpl();
				
				new CustomersListPresenter(customersListView).
					go(view.getContent());
			}
		}
	}

}
