/**
 * 
 */
package cbmarc.ginvoicing.client.presenter;

import cbmarc.ginvoicing.client.view.customers.CustomersEditView;
import cbmarc.ginvoicing.client.view.customers.CustomersListView;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author MCOSTA
 *
 */
public class CustomersPresenter implements Presenter {
	
	public interface Display {
		HasWidgets getContent();
		Widget asWidget();
	}
	
	protected final Display display;

	private CustomersListPresenter customersListPresenter;
	private CustomersEditPresenter customersEditPresenter;
	
	public CustomersPresenter(Display display) {
	    this.display = display;

	    customersListPresenter = new CustomersListPresenter(new CustomersListView());
	    customersEditPresenter = new CustomersEditPresenter(new CustomersEditView());
		
	    bind();
	}
	
	private void bind() {
	}
	
	public void updateDataFromDisplay() {}
	public void updateDisplayFromData() {}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		processHistoryToken();
	    container.add(display.asWidget());
	}
	
	public void processHistoryToken() {
		String token = History.getToken();
		
		if(token != null) {
			Presenter presenter = customersListPresenter;
			
			if(token.startsWith("main/customers/edit")) {
				presenter = customersEditPresenter;
			}

			presenter.go(display.getContent());
		}
	}

}
