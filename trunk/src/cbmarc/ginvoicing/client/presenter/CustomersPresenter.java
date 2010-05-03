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
		
	protected final CustomersView view;

	private CustomersListPresenter customersListPresenter = new CustomersListPresenter(new CustomersListViewImpl());
	private CustomersEditPresenter customersEditPresenter = new CustomersEditPresenter(new CustomersEditViewImpl());
	
	/**
	 * @param view
	 */
	public CustomersPresenter(CustomersView view) {
	    this.view = view;
	    view.setPresenter(this);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		
		processHistoryToken();
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = customersListPresenter;
			
			if(token.startsWith("main/customers/edit")) {
				presenter = customersEditPresenter;
			}

			presenter.go(view.getContent());
		}
	}

}
